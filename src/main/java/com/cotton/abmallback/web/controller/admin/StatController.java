package com.cotton.abmallback.web.controller.admin;

import com.cotton.abmallback.mapper.StatMapper;
import com.cotton.base.common.RestResponse;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;


/**
 * AdminLoginController
 *
 * @author lareina_h
 * @version 1.0
 * @date 2018/6/29
 */
@RestController
@RequestMapping(value = "/admin/stat", produces = "application/json; charset=UTF-8")
public class StatController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StatMapper statMapper;

    @RequestMapping(value = "/getOrderStatusStats", method = {RequestMethod.GET})
    public RestResponse<List<Map<String, Long>>> get(@RequestParam(value = "gmtStart") String gmtStart, @RequestParam(value = "gmtEnd") String gmtEnd) {
        List<Map<String, Long>> listMap = statMapper.getOrderStatusStats(gmtStart, gmtEnd);
        return RestResponse.getSuccesseResponse(listMap);
    }


    @RequestMapping(value = "/memberStat", method = {RequestMethod.GET})
    public RestResponse<Map<String, Object>> memberStat() {
        Map<String, Object> resultMap = new HashMap<>(16);

        Date lastDayBegin = getLastDayBegin();
        Date lastDayEnd = getLastDayEnd();
        Date lastMonthBegin = getLastMonthBegin();
        Date lastMonthEnd = getLastMonthEnd();

        //平台用户量
        long memberTotalCount = statMapper.getTotalMember();
        resultMap.put("userTotalCount", memberTotalCount);

        //会员注册量
        long agentMemberCount = statMapper.getAgentMemberCount();
        resultMap.put("agentMemberCount", agentMemberCount);

        //昨日会员注册量
        long memberTotalCountLastDay = statMapper.getAgentMemberCountByTime(lastDayBegin, lastDayEnd);
        resultMap.put("agentMemberTotalCountLastDay", memberTotalCountLastDay);

        //上月会员注册量
        long memberTotalCountLastMonth = statMapper.getAgentMemberCountByTime(lastMonthBegin, lastMonthEnd);
        resultMap.put("agentMemberTotalCountLastMonth", memberTotalCountLastMonth);

        //总订单量
        long ordersTotalCount = statMapper.getTotalMember();
        resultMap.put("ordersTotalCount", ordersTotalCount);

        //昨日会员订单数
        long ordersTotalCountLastDay = statMapper.getOrdersCountByTime(lastDayBegin, lastDayEnd);
        resultMap.put("ordersTotalCountLastDay", ordersTotalCountLastDay);

        //上月会员订单数
        long ordersTotalCountLastMonth = statMapper.getOrdersCountByTime(lastDayBegin, lastDayEnd);
        resultMap.put("ordersTotalCountLastMonth", ordersTotalCountLastMonth);

        //总购物额度
        BigDecimal orderMoney = statMapper.getOrderMoney();
        //累积会员人均订单数=累积至昨日会员订单总数/累积至昨日会员总数
        resultMap.put("memberOrdersCountAvg", String.format("%.2f", (double) ordersTotalCount / agentMemberCount));

        //累积会员人均购物额=累积至昨日会员购物总额/累积至昨日会员总数
        resultMap.put("memberOrdersMoneyAvg", String.format("%.2f", orderMoney.divide(BigDecimal.valueOf(agentMemberCount).multiply(BigDecimal.valueOf(100)), 2, BigDecimal.ROUND_HALF_EVEN)));


        //复购率=累积至昨日复够用户数/累积至昨日用户数
        long repurchaseMemberCount = statMapper.getRepurchaseMemberCount();
        resultMap.put("repurchasePercent", String.format("%.2f", (double) repurchaseMemberCount / memberTotalCount));

        //上月复购率
        long repurchaseMemberCountLastMonth = statMapper.getRepurchaseMemberCountByTime(lastMonthBegin, lastMonthEnd);
        if (memberTotalCountLastMonth > 0) {
            resultMap.put("repurchasePercentLastMonth", String.format("%.2f", (double) repurchaseMemberCountLastMonth / memberTotalCountLastMonth));
        } else {
            resultMap.put("repurchasePercentLastMonth", 0);

        }

        //用户购买率=累积至昨日有效订单用户数/累积至昨日用户总数
        resultMap.put("memberBuyPercent", String.format("%.2f", (double) agentMemberCount / memberTotalCount));

        return RestResponse.getSuccesseResponse(resultMap);
    }

    @RequestMapping(value = "/ordersRank", method = {RequestMethod.GET})
    public RestResponse<PageInfo<Map<String, Object>>> ordersRank(@RequestParam(defaultValue = "1") int pageNum,
                                                                  @RequestParam(defaultValue = "4") int pageSize,
                                                                  @RequestParam(value = "gmtStart") String gmtStart,
                                                                  @RequestParam(value = "gmtEnd") String gmtEnd,
                                                                  @RequestParam(value = "sortKey",defaultValue = "id") String sortKey,
                                                                  @RequestParam(value = "sortOrder",defaultValue = "desc") String sortOrder) {
        PageInfo<Map<String, Object>> listPageInfo = new PageInfo<>();

        listPageInfo.setTotal(statMapper.countOrdersRank(gmtStart, gmtEnd));
        listPageInfo.setList(statMapper.ordersRank((pageNum - 1) * pageSize, pageSize, gmtStart, gmtEnd, sortKey, sortOrder));

        return RestResponse.getSuccesseResponse(listPageInfo);
    }

    @RequestMapping(value = "/getSalesMoneyStat", method = {RequestMethod.GET})
    public RestResponse<Map<String, Object>> getSalesMoneyStat() {
        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put("totalSalesMoney", statMapper.getTotalSaleMoney(new Date(0L), new Date()));
        resultMap.put("lastMonthSalesMoney", statMapper.getTotalSaleMoney(getLastMonthBegin(), getLastMonthEnd()));
        resultMap.put("lastlastMonthSalesMoney", statMapper.getTotalSaleMoney(getLastLastMonthBegin(), getLastLastMonthEnd()));
        resultMap.put("yesterdaySalesMoney", statMapper.getTotalSaleMoney(getLastDayBegin(), getLastDayEnd()));
        resultMap.put("totalOrdersCount", statMapper.getTotalOrders());

        return RestResponse.getSuccesseResponse(resultMap);
    }
    @RequestMapping(value = "/getSalesMoneyTrend", method = {RequestMethod.GET})
    public RestResponse<List<Map<String, BigDecimal>>> getSalesMoneyTrend(
            @RequestParam(value = "gmtStart") String gmtStart,
            @RequestParam(value = "gmtEnd") String gmtEnd
    ) {
        return RestResponse.getSuccesseResponse(statMapper.getSalesMoneyTrend(gmtStart, gmtEnd));
    }

    @RequestMapping(value = "/getYearStat", method = {RequestMethod.GET})
    public RestResponse<List<Map<String, Object>>> getYearStat(
            @RequestParam(value = "year") Integer year
    ) {
        List<Map<String, Object>> resultMap = new ArrayList<>();
        for (int i=1; i<13; i++) {
            Map<String, Object> monthResult = new TreeMap<>();
            Date start = getMonthBegin(year, i);
            Date end = getMonthEnd(year, i);
            monthResult.put("id", i);
            monthResult.put("month", String.format("%d-%02d", year, i));
            monthResult.put("totalSoldMoney", statMapper.getTotalSaleMoney(start, end));
            monthResult.put("alipayTotalMoney", statMapper.getTotalSaleMoney(start, end, "alipay"));
            monthResult.put("alipayTotalCount", statMapper.getOrdersCountByTime(start, end, "'alipay"));
            monthResult.put("wechatTotalMoney", statMapper.getTotalSaleMoney(start, end, "wechat"));
            monthResult.put("wechatTotalCount", statMapper.getOrdersCountByTime(start, end, "'wechat"));
            monthResult.put("orderCount", statMapper.getOrdersCountByTime(start, end));
            monthResult.put("totalRebateMoney", statMapper.getTotalRebateMoney(start, end));
            resultMap.add(monthResult);
        }
        return RestResponse.getSuccesseResponse(resultMap);
    }

    public Date getLastDayBegin() {
        //昨天零点
        ZoneId zone = ZoneId.systemDefault();
        return Date.from(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN).atZone(zone).toInstant());
    }

    public Date getLastDayEnd() {
        //昨天结束
        ZoneId zone = ZoneId.systemDefault();
        return Date.from(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX).atZone(zone).toInstant());
    }

    public Date getLastMonthBegin() {
        LocalDate today = LocalDate.now();
        LocalDate firstday = LocalDate.of(today.getYear(), today.getMonth().minus(1), 1);
        ZoneId zone = ZoneId.systemDefault();
        return Date.from(firstday.atStartOfDay(zone).toInstant());
    }

    public Date getLastMonthEnd() {
        LocalDate today = LocalDate.now();
        LocalDate lastday = today.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        ZoneId zone = ZoneId.systemDefault();
        return Date.from(LocalDateTime.of(lastday, LocalTime.MAX).atZone(zone).toInstant());
    }

    public Date getLastLastMonthBegin() {
        LocalDate today = LocalDate.now();
        LocalDate firstday = LocalDate.of(today.getYear(), today.getMonth().minus(2), 1);
        ZoneId zone = ZoneId.systemDefault();
        return Date.from(firstday.atStartOfDay(zone).toInstant());
    }

    public Date getLastLastMonthEnd() {
        LocalDate today = LocalDate.now();
        LocalDate lastday = today.minusMonths(2).with(TemporalAdjusters.lastDayOfMonth());
        ZoneId zone = ZoneId.systemDefault();
        return Date.from(LocalDateTime.of(lastday, LocalTime.MAX).atZone(zone).toInstant());
    }

    public Date getMonthBegin(int year, int month) {
        LocalDate firstday = LocalDate.of(year, month, 1);
        ZoneId zone = ZoneId.systemDefault();
        return Date.from(firstday.atStartOfDay(zone).toInstant());
    }

    public Date getMonthEnd(int year, int month) {
        LocalDate lastday = LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth());
        ZoneId zone = ZoneId.systemDefault();
        return Date.from(lastday.atStartOfDay(zone).toInstant());
    }

    public static void main(String[] args) {
        StatController statController = new StatController();
        System.out.println(statController.getLastDayBegin());
        System.out.println(statController.getLastDayEnd());
        System.out.println(statController.getLastMonthBegin());
        System.out.println(statController.getLastMonthEnd());
        System.out.println(statController.getLastLastMonthBegin());
        System.out.println(statController.getLastLastMonthEnd());
    }
}
