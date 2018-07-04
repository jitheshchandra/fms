package com.ssrv.fms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssrv.fms.model.Relationships;
import com.ssrv.fms.service.Relationship.intf.RelationshipServiesIntf;
import com.ssrv.fms.vo.Relationship.RelationshipForm;

@Controller
public class RelationController {

	@Autowired
	RelationshipServiesIntf relationshipInf;

	
	/// Relation Summary
	@RequestMapping(value = "/RelationshipSummary", method = RequestMethod.GET)
	public String relationSummary(ModelMap model) 
	{
	try {
			List<Relationships> relation = relationshipInf.getAllRelation();
			model.addAttribute("relation", relation);
			return "RelationshipSummary";
		} 
	catch (Exception ex)
	    {
			System.out.println(ex.getMessage());
			return "ErrorMsg";
		}

	}

	
	/// Save Relation
	@RequestMapping(value = "/SaveRelation", method = RequestMethod.POST)
	public String saveRelationship(RelationshipForm form, ModelMap model) 
	{
		relationshipInf.SaveRelation(form);
		return "redirect:/RelationshipSummary";

	}

	
	/// Delete Relation
	@RequestMapping(value = "/DeleteRelation", method = RequestMethod.GET)
	public String deleteRelation(@RequestParam("id") int id)
	{
		relationshipInf.deleteRelation(id);
		return "redirect:/RelationshipSummary";
	}

	
	/// Edit Relation
	@RequestMapping(value = "/EditRelation", method = RequestMethod.GET)
	public String editRelation(@RequestParam("id") long id, ModelMap model) 
	{
		Relationships relation = relationshipInf.getRelationByID(id);
		model.addAttribute("relation", relation);
		return "EditRelationship";

	}

	
	/// Update Relation
	@RequestMapping(value = "/updateRelation", method = RequestMethod.POST)
	public String updateRelation(RelationshipForm form, ModelMap model) 
	{
		relationshipInf.updateRelation(form);
		return "redirect:/RelationshipSummary";
	}
}
