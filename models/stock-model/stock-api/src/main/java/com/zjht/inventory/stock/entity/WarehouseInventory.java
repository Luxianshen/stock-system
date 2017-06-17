package com.zjht.inventory.stock.entity;

public class WarehouseInventory {

	/** 主键 */
	private Long id;
	/** 仓库主键 */
	private String warehouseId;
	/** 物料主键 */
	private String materialId;
	/** 库存数量 */
	private Number inventoryCount;
	/** 备注 */
	private String remark;
	/** 盘点状态 */
	private String checkStatus;
	/** 库存明细表 */
	private InventoryDetailed inventoryDetailed;
	/** 物料表 */
	private Materiel material;
	/** 种类表 */
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public Number getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(Number inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public InventoryDetailed getInventoryDetailed() {
		return inventoryDetailed;
	}

	public void setInventoryDetailed(InventoryDetailed inventoryDetailed) {
		this.inventoryDetailed = inventoryDetailed;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Override
	public String toString() {
		return "WarehouseInventory [id=" + id + ", warehouseId=" + warehouseId + ", materialId=" + materialId
				+ ", inventoryCount=" + inventoryCount + ", remark=" + remark + ", checkStatus=" + checkStatus
				+ ", inventoryDetailed=" + inventoryDetailed + ", material=" + material + ", category=" + category
				+ "]";
	}

	public void setMaterial(Materiel material) {
		this.material = material;
	}
}
