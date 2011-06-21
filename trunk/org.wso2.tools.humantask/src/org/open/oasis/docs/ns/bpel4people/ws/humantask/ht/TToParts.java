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
 * A representation of the model object '<em><b>TTo Parts</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TToParts#getToPart <em>To Part</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTToParts()
 * @model extendedMetaData="name='tToParts' kind='elementOnly'"
 * @generated
 */
public interface TToParts extends TExtensibleElements {
	/**
	 * Returns the value of the '<em><b>To Part</b></em>' containment reference list.
	 * The list contents are of type {@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TToPart}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Part</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Part</em>' containment reference list.
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTToParts_ToPart()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='toPart' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TToPart> getToPart();

} // TToParts
