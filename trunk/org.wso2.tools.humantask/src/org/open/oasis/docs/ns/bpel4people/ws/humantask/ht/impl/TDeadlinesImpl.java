/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TDeadline;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TDeadlines;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TDeadlines</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TDeadlinesImpl#getStartDeadline <em>Start Deadline</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TDeadlinesImpl#getCompletionDeadline <em>Completion Deadline</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TDeadlinesImpl extends TExtensibleElementsImpl implements TDeadlines {
	/**
	 * The cached value of the '{@link #getStartDeadline() <em>Start Deadline</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDeadline()
	 * @generated
	 * @ordered
	 */
	protected EList<TDeadline> startDeadline;

	/**
	 * The cached value of the '{@link #getCompletionDeadline() <em>Completion Deadline</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompletionDeadline()
	 * @generated
	 * @ordered
	 */
	protected EList<TDeadline> completionDeadline;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TDeadlinesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return htdPackage.Literals.TDEADLINES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TDeadline> getStartDeadline() {
		if (startDeadline == null) {
			startDeadline = new EObjectContainmentEList<TDeadline>(TDeadline.class, this, htdPackage.TDEADLINES__START_DEADLINE);
		}
		return startDeadline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TDeadline> getCompletionDeadline() {
		if (completionDeadline == null) {
			completionDeadline = new EObjectContainmentEList<TDeadline>(TDeadline.class, this, htdPackage.TDEADLINES__COMPLETION_DEADLINE);
		}
		return completionDeadline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case htdPackage.TDEADLINES__START_DEADLINE:
				return ((InternalEList<?>)getStartDeadline()).basicRemove(otherEnd, msgs);
			case htdPackage.TDEADLINES__COMPLETION_DEADLINE:
				return ((InternalEList<?>)getCompletionDeadline()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case htdPackage.TDEADLINES__START_DEADLINE:
				return getStartDeadline();
			case htdPackage.TDEADLINES__COMPLETION_DEADLINE:
				return getCompletionDeadline();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case htdPackage.TDEADLINES__START_DEADLINE:
				getStartDeadline().clear();
				getStartDeadline().addAll((Collection<? extends TDeadline>)newValue);
				return;
			case htdPackage.TDEADLINES__COMPLETION_DEADLINE:
				getCompletionDeadline().clear();
				getCompletionDeadline().addAll((Collection<? extends TDeadline>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case htdPackage.TDEADLINES__START_DEADLINE:
				getStartDeadline().clear();
				return;
			case htdPackage.TDEADLINES__COMPLETION_DEADLINE:
				getCompletionDeadline().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case htdPackage.TDEADLINES__START_DEADLINE:
				return startDeadline != null && !startDeadline.isEmpty();
			case htdPackage.TDEADLINES__COMPLETION_DEADLINE:
				return completionDeadline != null && !completionDeadline.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TDeadlinesImpl
