/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.open.oasis.docs.ns.bpel4people.ws.humantask.ht;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TReassignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TReassignment#getPotentialOwners <em>Potential Owners</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTReassignment()
 * @model extendedMetaData="name='tReassignment' kind='elementOnly'"
 * @generated
 */
public interface TReassignment extends TExtensibleElements {
	/**
	 * Returns the value of the '<em><b>Potential Owners</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Potential Owners</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Potential Owners</em>' containment reference.
	 * @see #setPotentialOwners(TGenericHumanRole)
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTReassignment_PotentialOwners()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='potentialOwners' namespace='##targetNamespace'"
	 * @generated
	 */
	TGenericHumanRole getPotentialOwners();

	/**
	 * Sets the value of the '{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TReassignment#getPotentialOwners <em>Potential Owners</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Potential Owners</em>' containment reference.
	 * @see #getPotentialOwners()
	 * @generated
	 */
	void setPotentialOwners(TGenericHumanRole value);

} // TReassignment
