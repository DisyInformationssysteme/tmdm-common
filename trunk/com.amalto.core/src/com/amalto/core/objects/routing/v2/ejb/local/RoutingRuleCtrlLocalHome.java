/*
 * Generated by XDoclet - Do not edit!
 */
package com.amalto.core.objects.routing.v2.ejb.local;

/**
 * Local home interface for RoutingRuleCtrl.
 * @xdoclet-generated at 25-06-09
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface RoutingRuleCtrlLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/RoutingRuleCtrlLocal";
   public static final String JNDI_NAME="amalto/local/core/routingrulectrl";

   public com.amalto.core.objects.routing.v2.ejb.local.RoutingRuleCtrlLocal create()
      throws javax.ejb.CreateException;

}
