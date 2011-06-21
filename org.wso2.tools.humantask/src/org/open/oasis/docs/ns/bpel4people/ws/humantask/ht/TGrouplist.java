/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.open.oasis.docs.ns.bpel4people.ws.humantask.ht;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TGrouplist</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TGrouplist#getGroup <em>Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTGrouplist()
 * @model extendedMetaData="name='tGrouplist' kind='elementOnly'"
 * @generated
 */
public interface TGrouplist extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTGrouplist_Group()
	 * @model unique="false" dataType="org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TGroup"
	 *        extendedMetaData="kind='element' name='group' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<String> getGroup();

} // TGrouplist
