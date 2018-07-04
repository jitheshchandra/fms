package com.ssrv.fms.vo.material;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ssrv.fms.model.Materialindents;
import com.ssrv.fms.model.branch.Branch;
import com.ssrv.fms.model.incident.Indentdetails;
import com.ssrv.fms.model.item.Itemconsumption;
import com.ssrv.fms.model.item.Itemdeliverydetails;
import com.ssrv.fms.model.item.Itemstocklevels;
import com.ssrv.fms.model.managedentity.ManagedEntityGroup;
import com.ssrv.fms.model.organization.Organization;
import com.ssrv.fms.vo.indent.IndentDetailInfoForm;
import com.ssrv.fms.vo.indent.IndentDetailInfoWrapper;

public class MaterialIndentInfoView implements MaterialIndentInfo
	{
		private final Materialindents materialIndent;

		private final List<IndentDetailInfoForm> indentDetails;

		@SuppressWarnings("unchecked")
		public MaterialIndentInfoView(Materialindents materialIndentInfo)
			{
				this.materialIndent = materialIndentInfo;
				List<Indentdetails> indentDetailList = materialIndent.getIndentdetails();
				indentDetails = new ArrayList<IndentDetailInfoForm>();
				for (Indentdetails indentDetail : indentDetailList)
					{
						indentDetails.addAll((Collection<? extends IndentDetailInfoForm>) new IndentDetailInfoWrapper(indentDetail));
					}
			}

		@Override
		public Long getId()
			{
				return materialIndent.getId();
			}

		@Override
		public Branch getBranch()
			{
				return materialIndent.getBranch();
			}

		@Override
		public String getName()
			{
				return materialIndent.getName();
			}

		@Override
		public List<IndentDetailInfoForm> getIndentDetails()
			{
				return indentDetails;
			}

		public Collection<Itemdeliverydetails> getItemdeliverydetailsCollection()
			{
				return materialIndent.getItemdeliverydetailsCollection();
			}

		public Collection<Itemstocklevels> getItemstocklevelsCollection()
			{
				return materialIndent.getItemstocklevelsCollection();
			}

		public Itemconsumption getItemconsumption()
			{
				return (Itemconsumption) materialIndent.getItemconsumption();
			}

		@Override
		public Organization getOrganization()
			{
				return materialIndent.getOrganization();
			}

		@Override
		public ManagedEntityGroup getManageEntity()
			{
			ManagedEntityGroup me = new ManagedEntityGroup();
				return me ;//materialIndent.getManagedEntity();
			}
		public Materialindents getMaterialIndent()
			{
				return materialIndent;
			}
	}
