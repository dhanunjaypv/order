package com.bicgraphic.ods.order.constants;

public class OdsConstants {

	// Order properties
	public static final String REQUEST_MAPPING_APPLICATION_JSON = "application/json";
	public static final String ORDER_COLLECTION = "OrdersCollection";
	public static final String ORDER_DATA = "OrderDB";
	public static final String ORDER_SUCCESS = "SUCCESS";
	public static final String ORDER_FAIL = "FAIL";
	public static final String ORDER_DELETE_RESPONSE = "Successfully deleted order record";
	public static final String ORDER_UPDATE_RESPONSE = "Successfully updated order record";
	public static final String ORDER_CREATE_RESPONSE = "Successfully created order record";
	public static final String ORDER_POST_OPERATION = "CREATE";
	public static final String ORDER_UPDATE_OPERATION = "UPDATE";
	public static final String ORDER_DELETE_OPERATION = "DELETE";

	// Response Codes
	public static final String ORDER_SUCCESS_CODE = "INTERR-000";
	public static final String ORDER_FAIL_CODE = "INTERR-001";
	public static final String ORDER_INV_STRUCTURE_CODE = "INTERR-111";
	public static final String ORDER_MANDATORY_MISS_CODE = "INTERR-112";
	public static final String ORDER_INV_INPUT_CODE = "INTERR-113";
	public static final String ORDER_DATA_NOT_FOUND_CODE = "INTERR-114";
	public static final String ORDER_UNKNOWN_ERR_CODE = "INTERR-115";
	public static final String ORDER_EVENT_NETWORK_ERR_CODE = "EVENT-ERR";
	public static final String ORDER_ALREADYEXITS_CODE = "INTERR-003";
	public static final String ORDER_DOESNOTEXITS_CODE = "INTERR-004";

	// Response Messages
	public static final String ORDER_SUCCESS_MSG = "Operation Successfully completed";
	public static final String ORDER_INV_STRUCTURE_MSG = "Invalid Data Structure in Request.";
	public static final String ORDER_MANDATORY_MISS_MSG = "Mandatory request field missing.";
	public static final String ORDER_INV_INPUT_MSG = "Incorrect request field value";
	public static final String ORDER_DATA_NOT_FOUND_MSG = "No Records found for given input";
	public static final String ORDER_UNKNOWN_ERR_MSG = "OOPS! Something went wrong";
	public static final String ORDER_FAIL_MSG = "Operation Fail!";
	public static final String ORDER_SALESFORCE_CONNECTION_FAILURE_MSG = "Failed to connect Salesforce";
	public static final String ORDER_SALESFORCE_CONNECTION_FAILURE_CODE = "INTERR-116";
	public static final String ORDER_EVENT_NETWORK_ERR_MSG = "Event insertion failed";
	public static final String ORDER_ALREADYEXITS_MSG = "Order Already Exits";
	public static final String ORDER_DOESNOTEXITS_MSG = "Order Does not Exits";

}
