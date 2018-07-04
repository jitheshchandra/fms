package com.ssrv.fms.vo.incidents.impl;

import com.ssrv.fms.vo.incidents.EscalationLevelInfo;

public class EscalationLevelInfoForm implements EscalationLevelInfo
	{
		private Long id;
		private String name;

		@Override
		public Long getId()
			{
				return id;
			}

		@Override
		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

	}
