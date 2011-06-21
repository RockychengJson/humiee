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
 * A representation of the model object '<em><b>TUserlist</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TUserlist#getUser <em>User</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTUserlist()
 * @model extendedMetaData="name='tUserlist' kind='elementOnly'"
 * @generated
 */
public interface TUserlist extends EObject {
	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' attribute list.
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTUserlist_User()
	 * @model unique="false" dataType="org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TUser"
	 *        extendedMetaData="kind='element' name='user' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<String> getUser();

} // TUserlist
