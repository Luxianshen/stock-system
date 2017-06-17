package com.zjht.commons.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason on 2016/11/7 0007.
 */
public class StatusUtil {
	/**
	 * 库存状态
	 */
	public static final long STOCK_OUT_PRODUCE = 1;// 生产出库
	public static final long STOCK_OUT_ALLOCATION = 2;// 调拨出库
	public static final long STOCK_OUT_REPAIR = 3;// 返修出库
	public static final long STOCK_OUT_EXCHANGE = 4;// 换机出库
	public static final long STOCK_OUT_BORROW = 5;// 借用出库
	public static final long STOCK_OUT_MISSING = 6;// 报丢出库
	public static final long STOCK_OUT_SCRAP = 7;// 报废出库

	public static final long STOCK_LOCK = 8;// 锁定
	public static final long STOCK_NORMAL = 9;// 正常

	public static final long STOCK_IN_CHECK = 14;// 盘点入库
	public static final long STOCK_IN_PURCHASE = 15;// 采购入库
	public static final long STOCK_IN_PRODUCE = 16;// 生产入库
	public static final long STOCK_IN_ALLOCATION = 17;// 调拨入库
	public static final long STOCK_IN_REPAIR = 18;// 返修入库
	public static final long STOCK_IN_EXCHANGE = 19;// 换机入库
	public static final long STOCK_IN_BORROW = 20;// 借用入库
	public static final long STOCK_IN_DIFFER = 21;// 差异入库
	// 入库
	public static final Map<Long,String> ins = new HashMap();
	// 出库
	public static final Map<Long,String> outs = new HashMap();
	// 所有
	public static final Map<Long,String> all = new HashMap();
	static {
		outs.put(STOCK_OUT_PRODUCE,		"生产出库");
		outs.put(STOCK_OUT_ALLOCATION,	"调拨出库");
		outs.put(STOCK_OUT_REPAIR,		"返修出库");
		outs.put(STOCK_OUT_EXCHANGE,	"换机出库");
		outs.put(STOCK_OUT_BORROW,		"借用出库");
		outs.put(STOCK_OUT_MISSING,		"报丢出库");
		outs.put(STOCK_OUT_SCRAP,		"报废出库");
		all.putAll(outs);

		all.put(STOCK_LOCK,				"锁定");
		all.put(STOCK_NORMAL,			"正常");

		ins.put(STOCK_IN_CHECK,			"盘点入库");
		ins.put(STOCK_IN_PURCHASE,		"采购入库");
		ins.put(STOCK_IN_PRODUCE,		"生产入库");
		ins.put(STOCK_IN_ALLOCATION,	"调拨入库");
		ins.put(STOCK_IN_REPAIR,		"返修入库");
		ins.put(STOCK_IN_EXCHANGE,		"换机入库");
		ins.put(STOCK_IN_BORROW,		"借用入库");
		ins.put(STOCK_IN_DIFFER,		"差异入库");
		all.putAll(ins);

	}

	/**
	 * 差异处理结果: 1.未处理 2.已处理-盘点入库 3.已处理-变更状态 4.无差异
	 */
	public static final long DIFFER_HANDLE_NO = 1;
	public static final long DIFFER_HANDLE_IN_CHECK = 2;
	public static final long DIFFER_HANDLE_CHANGE_STATUS = 3;
	public static final long DIFFER_NO_NEED_TO = 4;

	/**
	 * 处理结果: 1待处理 2已入库 3已出库 4已锁定 5已解锁 6已作废
	 */
	public static final long HANDLE_RESULT_AWAIT = 1;
	public static final long HANDLE_RESULT_INBOUND = 2;
	public static final long HANDLE_RESULT_OUTBOUND = 3;
	public static final long HANDLE_RESULT_LOCK = 4;
	public static final long HANDLE_RESULT_UNLOCK = 5;
	public static final long HANDLE_RESULT_NULLIFY = 6;

	/**
	 * 判断s中是否包含l
	 * 
	 * @param maps
	 *            出/入库类型集合
	 * @param l
	 *            出入库类型
	 * @return 包含返回true, 不包含返回false
	 */
	public static boolean isInOut(Map<Long,String> maps, long l) {
		return maps.containsKey(l);
	}

	/**
	 * 获取正确的处理结果
	 * 
	 * @param l
	 *            申请类型
	 * @return 正确无误后的处理结果
	 */
	public static long getHandleResult(long l) {
		if (ins.containsKey(l))
			return HANDLE_RESULT_INBOUND;
		if (outs.containsKey(l))
			return HANDLE_RESULT_OUTBOUND;
		if (STOCK_LOCK == l)
			return HANDLE_RESULT_LOCK;
		if (HANDLE_RESULT_NULLIFY == l)
			return HANDLE_RESULT_LOCK;
		return HANDLE_RESULT_UNLOCK;
	}

	public static String getHandleResultName(long l) {
		return l == HANDLE_RESULT_AWAIT ? "未处理"
				: (l == HANDLE_RESULT_INBOUND ? "已入库"
				: (l == HANDLE_RESULT_OUTBOUND ? "已出库"
				: (l == HANDLE_RESULT_LOCK ? "已锁定"
				: (l == HANDLE_RESULT_UNLOCK ? "已解锁"
				: (l == HANDLE_RESULT_NULLIFY ? "已作废"
				: ("未知状态"))))));
	}

	public static String getDifferHandleStatusName(long l) {
		return l == DIFFER_HANDLE_NO ? "未处理"
				: (l == DIFFER_HANDLE_IN_CHECK ? "已处理-盘点入库"
						: (l == DIFFER_HANDLE_CHANGE_STATUS ? "已处理-变更状态"
								: (l == DIFFER_NO_NEED_TO ? "无差异" : ("未知状态"))));
	}

	/**
	 * 差异类型：1.不在系统 2.不在仓库3.状态不实 4.正常
	 */
	public static final long DIFFER_TYPR_NOT_IN_SYSTEM = 1;
	public static final long DIFFER_TYPR_NOT_IN_STOCK = 2;
	public static final long DIFFER_TYPR_NOT_IN_STATUS = 3;
	public static final long DIFFER_TYPR_NORMAL_STATUS = 4;

	/**
	 * 处理结果: 1.未处理 2.已处理-盘点入库 3.已处理-重新入库 4.报丢出库 5.还机处理 6.无差异
	 */
	public static final long HANDLE_RESULT_NOT_START = 1;
	public static final long HANDLE_RESULT_TO_INVENTORY = 2;
	public static final long HANDLE_RESULT_RE_INVENTORY = 3 ; 
	public static final long HANDLE_RESULT_LOSE = 4 ; 
	public static final long HANDLE_RESULT_RETURN = 5 ; 
	public static final long HANDLE_RESULT_NO_DIFFER = 6;

	/**
	 * 盘点状态：0.未开始，1.处理中，2.可处理，3.审批中，4.已完成 ，5.等待中
	 */
	public static final long RESULT_STATUS_NOT_START = 0;
	public static final long RESULT_STATUS_IN_DEAL = 1;
	public static final long RESULT_STATUS_CAN_DEAL = 2;
	public static final long RESULT_STATUS_APPROVAL = 3;
	public static final long RESULT_STATUS_FINISHED = 4;
	public static final long RESULT_STATUS_WAITING = 5;

	/**
	 * 盘点结果: 1.无差异 2.有差异
	 */
	public static final long PLAN_RESULT_NO_DIFFER = 1;
	public static final long PLAN_RESULT_DIFFER = 2;
	
	/**
	 * 盘点状态 0.在仓库 1.不在仓库
	 */
	public static final long PLAN_STATUS_IN_STOCK = 0;
	public static final long PLAN_STATUS_NOT_IN_STOCK = 1;

	/**
	 * 导入数据的行
	 */
	public static final int IMPORT_SEQUENCE_COLUMN = 0; // 序号列
	public static final int IMPORT_MATERIEL_NAME_COLUMN = 1; // 物料名称列
	public static final int IMPORT_ASSET_NO_COLUMN = 2;// 资产编号所在的列
	public static final int IMPORT_STATUS_COLUMN = 3;// 盘点状态所在的列
	public static final int IMPORT_DIFFER_REMARK = 4; // 非正常原因所在的列
	public static final int IMPORT_SHEET_HEADER_LEGTH = 5;// sheet表头部的长度

	/**
	 * 仓库状态：1.正常 0.已删除
	 */
	public static final long WAREHOUSE_NORMAL = 1;
	public static final long WAREHOUSE_DELETED = 0;


}
