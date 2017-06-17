package com.zjht.inventory.stock.entity;

/**
 * 库存明细表 (仅计件使用)
 */
public class InventoryDetailed {

	/** 主键 */
	private Long id;
	/** 仓库物料主键 */
	private String warehouseMaterialId;
	/** 资产编号--->共有属性 */
	private String assetsNo;
	/** 库存状态--->共有属性 */
	private Long status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWarehouseMaterialId() {
		return warehouseMaterialId;
	}

	public void setWarehouseMaterialId(String warehouseMaterialId) {
		this.warehouseMaterialId = warehouseMaterialId;
	}

	public String getAssetsNo() {
		return assetsNo;
	}

	public void setAssetsNo(String assetsNo) {
		this.assetsNo = assetsNo;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "InventoryDetailed [id=" + id + ", warehouseMaterialId=" + warehouseMaterialId + ", assetsNo=" + assetsNo
				+ ", status=" + status + "]";
	}

}
