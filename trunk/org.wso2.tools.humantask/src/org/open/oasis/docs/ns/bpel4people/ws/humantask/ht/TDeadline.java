/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.open.oasis.docs.ns.bpel4people.ws.humantask.ht;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TDeadline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TDeadline#getFor <em>For</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TDeadline#getUntil <em>Until</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TDeadline#getEscalation <em>Escalation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTDeadline()
 * @model extendedMetaData="name='tDeadline' kind='elementOnly'"
 * @generated
 */
public interface TDeadline extends TExtensibleElements {
	/**
	 * Returns the value of the '<em><b>For</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>For</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>For</em>' containment reference.
	 * @see #setFor(TDurationExpr)
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTDeadline_For()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='for' namespace='##targetNamespace'"
	 * @generated
	 */
	TDurationExpr getFor();

	/**
	 * Sets the value of the '{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TDeadline#getFor <em>For</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>For</em>' containment reference.
	 * @see #getFor()
	 * @generated
	 */
	void setFor(TDurationExpr value);

	/**
	 * Returns the value of the '<em><b>Until</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Until</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Until</em>' containment reference.
	 * @see #setUntil(TDeadlineExpr)
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTDeadline_Until()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='until' namespace='##targetNamespace'"
	 * @generated
	 */
	TDeadlineExpr getUntil();

	/**
	 * Sets the value of the '{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TDeadline#getUntil <em>Until</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Until</em>' containment reference.
	 * @see #getUntil()
	 * @generated
	 */
	void setUntil(TDeadlineExpr value);

	/**
	 * Returns the value of the '<em><b>Escalation</b></em>' containment reference list.
	 * The list contents are of type {@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TEscalation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Escalation</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Escalation</em>' containment reference list.
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTDeadline_Escalation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='escalation' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TEscalation> getEscalation();

} // TDeadline
