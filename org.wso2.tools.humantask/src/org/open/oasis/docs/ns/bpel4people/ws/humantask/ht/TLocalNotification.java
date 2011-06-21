/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.open.oasis.docs.ns.bpel4people.ws.humantask.ht;

import javax.xml.namespace.QName;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TLocal Notification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLocalNotification#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLocalNotification#getPeopleAssignments <em>People Assignments</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLocalNotification#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTLocalNotification()
 * @model extendedMetaData="name='tLocalNotification' kind='elementOnly'"
 * @generated
 */
public interface TLocalNotification extends TExtensibleElements {
	/**
	 * Returns the value of the '<em><b>Priority</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority</em>' containment reference.
	 * @see #setPriority(TPriority)
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTLocalNotification_Priority()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='priority' namespace='##targetNamespace'"
	 * @generated
	 */
	TPriority getPriority();

	/**
	 * Sets the value of the '{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLocalNotification#getPriority <em>Priority</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' containment reference.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(TPriority value);

	/**
	 * Returns the value of the '<em><b>People Assignments</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>People Assignments</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>People Assignments</em>' containment reference.
	 * @see #setPeopleAssignments(TPeopleAssignments)
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTLocalNotification_PeopleAssignments()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='peopleAssignments' namespace='##targetNamespace'"
	 * @generated
	 */
	TPeopleAssignments getPeopleAssignments();

	/**
	 * Sets the value of the '{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLocalNotification#getPeopleAssignments <em>People Assignments</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>People Assignments</em>' containment reference.
	 * @see #getPeopleAssignments()
	 * @generated
	 */
	void setPeopleAssignments(TPeopleAssignments value);

	/**
	 * Returns the value of the '<em><b>Reference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' attribute.
	 * @see #setReference(QName)
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTLocalNotification_Reference()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
	 *        extendedMetaData="kind='attribute' name='reference'"
	 * @generated
	 */
	QName getReference();

	/**
	 * Sets the value of the '{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLocalNotification#getReference <em>Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' attribute.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(QName value);

} // TLocalNotification
