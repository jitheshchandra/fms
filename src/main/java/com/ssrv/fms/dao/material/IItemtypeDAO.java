package com.ssrv.fms.dao.material;

import com.ssrv.fms.dao.IBaseDAO;
import com.ssrv.fms.model.Materialindents;
import com.ssrv.fms.model.item.Items;
import com.ssrv.fms.model.item.Itemstocklevels;

public interface IItemtypeDAO extends IBaseDAO {
	Itemstocklevels getItemStockLevel(Items item, Materialindents materialIndent);
}
