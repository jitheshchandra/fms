package com.ssrv.fms.vo.indent;

import com.ssrv.fms.model.incident.Indentdetails;

public class IndentDetailInfoView
	{
		private Long id;
		private String name;
		private Long itemId;
		private Float reOrderlevel;
		private Float orderQuantity;

		public IndentDetailInfoView()
		{
			
		}
		
		public IndentDetailInfoView(Indentdetails indentList)
		{
			this.name=indentList.getItems().getName();
			this.id=indentList.getId().getIndentId();
			this.itemId=indentList.getId().getItemId();
			this.orderQuantity=indentList.getQuantity();
			this.reOrderlevel=indentList.getReorderLevel();
		}
		
		public Long getItemId()
			{
				return itemId;
			}

		public void setItemId(Long itemId)
			{
				this.itemId = itemId;
			}

		public Long getId()
			{
				return id;
			}

		public void setId(Long id)
			{
				this.id = id;
			}

		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public Float getReOrderlevel()
			{
				return reOrderlevel;
			}

		public void setReOrderlevel(Float reOrderlevel)
			{
				this.reOrderlevel = reOrderlevel;
			}

		public Float getOrderQuantity()
			{
				return orderQuantity;
			}

		public void setOrderQuantity(Float orderQuantity)
			{
				this.orderQuantity = orderQuantity;
			}
	}
