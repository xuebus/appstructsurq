package com.asiainfo.ocdc.streaming.tools

import scala.collection.mutable
import org.apache.commons.jexl2._

object JexlTool {

  val engine=new JexlEngine()
  val expressions = new mutable.HashMap[String, Expression]()

  def getExpression(expression:String):Expression={
    expressions.getOrElse(expression,{
      engine.createExpression(expression)
    })
  }

  def getExpValue(expression:String,param:Array[(String,String)]):String={
    val context = new MapContext()
    param.foreach(x=>context.set(x._1,x._2))
    getExpression(expression).evaluate(context).toString
  }
}