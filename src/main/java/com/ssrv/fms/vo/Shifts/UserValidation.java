package com.ssrv.fms.vo.Shifts;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidation implements Validator
	{

		public boolean supports(Class<?> clazz)
			{

				return ShiftForm.class.equals(clazz);
			}

		public void validate(Object target, Errors errors)
			{
				ShiftForm form = (ShiftForm) target;

				//if (form.getName() == null)
					{
						errors.rejectValue("name", "Please Enter Shift Name");
					}

				if (form.getBranchId() == 0)
					{
						errors.rejectValue("branchId", "Please Select Branch");
					}
				if (form.getOrganizationId() == 0)
					{
						errors.rejectValue("organizationId", "Please Select Organization");

					}
				//if(form.getStartTime()== null)
					{
						errors.rejectValue("startTime", "Please Enter Start Time");
					}
				//if(form.getEndTime()==null)
					{
						errors.rejectValue("endTime", "Please Enter End Time");
					}

			}

	}
