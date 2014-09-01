
package com.joshj5hawk.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSummoningTable extends ModelBase
{
  //fields
    ModelRenderer summoningTableBase;
    ModelRenderer pilonOne;
    ModelRenderer pilonTwo;
    ModelRenderer pilonThree;
    ModelRenderer pilonFour;
    ModelRenderer summoningTableTop;
  
  public ModelSummoningTable()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      summoningTableBase = new ModelRenderer(this, 0, 0);
      summoningTableBase.addBox(-8F, 14F, -8F, 16, 10, 16);
      summoningTableBase.setRotationPoint(0F, 0F, 0F);
      summoningTableBase.setTextureSize(64, 64);
      summoningTableBase.mirror = true;
      setRotation(summoningTableBase, 0F, 0F, 0F);
      pilonOne = new ModelRenderer(this, 0, 43);
      pilonOne.addBox(0F, 0F, 0F, 1, 5, 1);
      pilonOne.setRotationPoint(-8F, 9F, 7F);
      pilonOne.setTextureSize(64, 64);
      pilonOne.mirror = true;
      setRotation(pilonOne, 0F, 0F, 0F);
      pilonTwo = new ModelRenderer(this, 4, 43);
      pilonTwo.addBox(0F, 0F, 0F, 1, 5, 1);
      pilonTwo.setRotationPoint(7F, 9F, 7F);
      pilonTwo.setTextureSize(64, 64);
      pilonTwo.mirror = true;
      setRotation(pilonTwo, 0F, 0F, 0F);
      pilonThree = new ModelRenderer(this, 8, 43);
      pilonThree.addBox(0F, 0F, 0F, 1, 5, 1);
      pilonThree.setRotationPoint(7F, 9F, -8F);
      pilonThree.setTextureSize(64, 64);
      pilonThree.mirror = true;
      setRotation(pilonThree, 0F, 0F, 0F);
      pilonFour = new ModelRenderer(this, 12, 43);
      pilonFour.addBox(-8F, 0F, -8F, 1, 5, 1);
      pilonFour.setRotationPoint(0F, 9F, 0F);
      pilonFour.setTextureSize(64, 64);
      pilonFour.mirror = true;
      setRotation(pilonFour, 0F, 0F, 0F);
      summoningTableTop = new ModelRenderer(this, 0, 26);
      summoningTableTop.addBox(0F, 0F, 0F, 16, 1, 16);
      summoningTableTop.setRotationPoint(-8F, 8F, -8F);
      summoningTableTop.setTextureSize(64, 64);
      summoningTableTop.mirror = true;
      setRotation(summoningTableTop, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    summoningTableBase.render(f5);
    pilonOne.render(f5);
    pilonTwo.render(f5);
    pilonThree.render(f5);
    pilonFour.render(f5);
    summoningTableTop.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
  
  public void renderModel(float f)
  {
	  summoningTableBase.render(f);
	  pilonOne.render(f);
	  pilonTwo.render(f);
	  pilonThree.render(f);
	  pilonFour.render(f);
	  /*summoningTableTop.render(f);*/
  }
  

}
