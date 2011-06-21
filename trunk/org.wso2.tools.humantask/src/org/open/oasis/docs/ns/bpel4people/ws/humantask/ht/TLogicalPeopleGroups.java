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
 * A representation of the model object '<em><b>TLogical People Groups</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLogicalPeopleGroups#getLogicalPeopleGroup <em>Logical People Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTLogicalPeopleGroups()
 * @model extendedMetaData="name='tLogicalPeopleGroups' kind='elementOnly'"
 * @generated
 */
public interface TLogicalPeopleGroups extends TExtensibleElements {
	/**
	 * Returns the value of the '<em><b>Logical People Group</b></em>' containment reference list.
	 * The list contents are of type {@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TLogicalPeopleGroup}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logical People Group</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Logical People Group</em>' containment reference list.
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTLogicalPeopleGroups_LogicalPeopleGroup()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='logicalPeopleGroup' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TLogicalPeopleGroup> getLogicalPeopleGroup();

} // TLogicalPeopleGroups
