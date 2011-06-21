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
 * A representation of the model object '<em><b>TTasks</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTasks#getTask <em>Task</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTTasks()
 * @model extendedMetaData="name='tTasks' kind='elementOnly'"
 * @generated
 */
public interface TTasks extends TExtensibleElements {
	/**
	 * Returns the value of the '<em><b>Task</b></em>' containment reference list.
	 * The list contents are of type {@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task</em>' containment reference list.
	 * @see org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage#getTTasks_Task()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='task' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TTask> getTask();

} // TTasks
