/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.open.oasis.docs.ns.bpel4people.ws.humantask.ht;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TOrganizational Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TOrganizationalEntity#getUsers <em>Users</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TOrganizationalEntity#getGroups <em>Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTOrganizationalEntity()
 * @model extendedMetaData="name='tOrganizationalEntity' kind='elementOnly'"
 * @generated
 */
public interface TOrganizationalEntity extends EObject {
	/**
	 * Returns the value of the '<em><b>Users</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Users</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Users</em>' containment reference.
	 * @see #setUsers(TUserlist)
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTOrganizationalEntity_Users()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='users' namespace='##targetNamespace'"
	 * @generated
	 */
	TUserlist getUsers();

	/**
	 * Sets the value of the '{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TOrganizationalEntity#getUsers <em>Users</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Users</em>' containment reference.
	 * @see #getUsers()
	 * @generated
	 */
	void setUsers(TUserlist value);

	/**
	 * Returns the value of the '<em><b>Groups</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groups</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groups</em>' containment reference.
	 * @see #setGroups(TGrouplist)
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTOrganizationalEntity_Groups()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='groups' namespace='##targetNamespace'"
	 * @generated
	 */
	TGrouplist getGroups();

	/**
	 * Sets the value of the '{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TOrganizationalEntity#getGroups <em>Groups</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groups</em>' containment reference.
	 * @see #getGroups()
	 * @generated
	 */
	void setGroups(TGrouplist value);

} // TOrganizationalEntity
