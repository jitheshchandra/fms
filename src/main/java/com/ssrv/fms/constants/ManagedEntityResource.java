package com.ssrv.fms.constants;

public interface ManagedEntityResource {

	public static final String ROOT_PATH = "managedentity";

	public static final String MANAGED_ENTITY_GROUP_SUMMARY = ROOT_PATH + "/"
			+ "getManagedEntityGroupSummary";
	public static final String MANAGED_ENTITY_GROUP_SUMMARY_MOBILE = ROOT_PATH
			+ "/" + "m.getManagedEntityGroupSummary";

	public static final String SHOW_MANAGED_ENTITY_GROUP_CREATION = ROOT_PATH
			+ "/" + "showManagedEntityGroupCreation";
	public static final String CREATE_NEW_EDIT_MANAGED_ENTITY_GROUP = ROOT_PATH
			+ "/" + "createManagedEntityGroup";
	public static final String CREATE_NEW_EDIT_MANAGED_ENTITY_GROUP_MOBILE = ROOT_PATH
			+ "/" + "m.createManagedEntityGroup";
	public static final String MANAGED_ENTITY_INSTANCES_VIEW_ATTR = "managedEntityInstances";

	public static final String MANAGED_ENTITY_GROUPSUMMARY_VIEW_ATTR = "managedEntityGroupSummaryView";

}
