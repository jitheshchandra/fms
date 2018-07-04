package com.ssrv.fms.vo.Asset;

import com.ssrv.fms.model.assets.AssetAttributeType;

public class AssetAttributeForm 
{
	
	 private String id;

	    private String name;

	    private String attributeType;
	    
	    AssetAttributeForm(String s)
	    {
	    	
	    }
	    
	    AssetAttributeForm()
	    {
	    	
	    }

	    public String getId ()
	    {
	        return id;
	    }

	    public void setId (String id)
	    {
	        this.id = id;
	    }

	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    public String getAttributeType ()
	    {
	        return attributeType;
	    }

	    public void setAttributeType (String attributeType)
	    {
	        this.attributeType = attributeType;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [id = "+id+", name = "+name+", attributeType = "+attributeType+"]";
	    }
	
	

}
