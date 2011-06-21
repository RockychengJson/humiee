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
 * A representation of the model object '<em><b>TPresentation Parameters</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationParameters#getPresentationParameter <em>Presentation Parameter</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationParameters#getExpressionLanguage <em>Expression Language</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTPresentationParameters()
 * @model extendedMetaData="name='tPresentationParameters' kind='elementOnly'"
 * @generated
 */
public interface TPresentationParameters extends TExtensibleElements {
	/**
	 * Returns the value of the '<em><b>Presentation Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationParameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Presentation Parameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Presentation Parameter</em>' containment reference list.
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTPresentationParameters_PresentationParameter()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='presentationParameter' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TPresentationParameter> getPresentationParameter();

	/**
	 * Returns the value of the '<em><b>Expression Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression Language</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression Language</em>' attribute.
	 * @see #setExpressionLanguage(String)
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTPresentationParameters_ExpressionLanguage()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
	 *        extendedMetaData="kind='attribute' name='expressionLanguage'"
	 * @generated
	 */
	String getExpressionLanguage();

	/**
	 * Sets the value of the '{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPresentationParameters#getExpressionLanguage <em>Expression Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression Language</em>' attribute.
	 * @see #getExpressionLanguage()
	 * @generated
	 */
	void setExpressionLanguage(String value);

} // TPresentationParameters
