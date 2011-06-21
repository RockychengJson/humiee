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
 * A representation of the model object '<em><b>TPresentation Elements</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationElements#getName <em>Name</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationElements#getPresentationParameters <em>Presentation Parameters</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationElements#getSubject <em>Subject</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationElements#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTPresentationElements()
 * @model extendedMetaData="name='tPresentationElements' kind='elementOnly'"
 * @generated
 */
public interface TPresentationElements extends TExtensibleElements {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference list.
	 * The list contents are of type {@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TText}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference list.
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTPresentationElements_Name()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TText> getName();

	/**
	 * Returns the value of the '<em><b>Presentation Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Presentation Parameters</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Presentation Parameters</em>' containment reference.
	 * @see #setPresentationParameters(TPresentationParameters)
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTPresentationElements_PresentationParameters()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='presentationParameters' namespace='##targetNamespace'"
	 * @generated
	 */
	TPresentationParameters getPresentationParameters();

	/**
	 * Sets the value of the '{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationElements#getPresentationParameters <em>Presentation Parameters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Presentation Parameters</em>' containment reference.
	 * @see #getPresentationParameters()
	 * @generated
	 */
	void setPresentationParameters(TPresentationParameters value);

	/**
	 * Returns the value of the '<em><b>Subject</b></em>' containment reference list.
	 * The list contents are of type {@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TText}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject</em>' containment reference list.
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTPresentationElements_Subject()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='subject' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TText> getSubject();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference list.
	 * The list contents are of type {@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TDescription}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' containment reference list.
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTPresentationElements_Description()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TDescription> getDescription();

} // TPresentationElements
