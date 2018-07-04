package com.ssrv.fms.vo.Asset;


public class AssetCatrgoryAttributeForm {
	private String category;

    private AssetAttributes[] assetAttributes;

    AssetCatrgoryAttributeForm()
    {
    	
    }
    
    
    AssetCatrgoryAttributeForm(String s)
    {
    	
    }
    
    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public AssetAttributes[] getAssetAttributes ()
    {
        return assetAttributes;
    }

    public void setAssetAttributes (AssetAttributes[] assetAttributes)
    {
        this.assetAttributes = assetAttributes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [category = "+category+", assetAttributes = "+assetAttributes+"]";
    }

}
