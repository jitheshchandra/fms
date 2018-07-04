package com.ssrv.fms.dao.material.impl;

import org.springframework.stereotype.Component;

import com.ssrv.fms.dao.impl.BaseDAO;
import com.ssrv.fms.dao.material.IItemtypeDAO;
import com.ssrv.fms.model.Materialindents;
import com.ssrv.fms.model.item.Items;
import com.ssrv.fms.model.item.Itemstocklevels;

@Component(value = "itemtypeJPADAO")
public class ItemtypeJPADAO extends BaseDAO implements IItemtypeDAO {

	@Override
	public Itemstocklevels getItemStockLevel(Items item,
			Materialindents materialIndent) {
		return null;
	}

}
