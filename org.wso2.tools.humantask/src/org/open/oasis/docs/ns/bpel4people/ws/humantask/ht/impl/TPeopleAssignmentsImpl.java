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

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TGenericHumanRole;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPeopleAssignments;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TPeople Assignments</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TPeopleAssignmentsImpl#getGenericHumanRole <em>Generic Human Role</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TPeopleAssignmentsImpl#getPotentialOwners <em>Potential Owners</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TPeopleAssignmentsImpl#getExcludedOwners <em>Excluded Owners</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TPeopleAssignmentsImpl#getTaskInitiator <em>Task Initiator</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TPeopleAssignmentsImpl#getTaskStakeholders <em>Task Stakeholders</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TPeopleAssignmentsImpl#getBusinessAdministrators <em>Business Administrators</em>}</li>
 *   <li>{@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.impl.TPeopleAssignmentsImpl#getRecipients <em>Recipients</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TPeopleAssignmentsImpl extends TExtensibleElementsImpl implements TPeopleAssignments {
	/**
	 * The cached value of the '{@link #getGenericHumanRole() <em>Generic Human Role</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenericHumanRole()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap genericHumanRole;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TPeopleAssignmentsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return htdPackage.Literals.TPEOPLE_ASSIGNMENTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGenericHumanRole() {
		if (genericHumanRole == null) {
			genericHumanRole = new BasicFeatureMap(this, htdPackage.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE);
		}
		return genericHumanRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TGenericHumanRole> getPotentialOwners() {
		return getGenericHumanRole().list(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__POTENTIAL_OWNERS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TGenericHumanRole> getExcludedOwners() {
		return getGenericHumanRole().list(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__EXCLUDED_OWNERS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TGenericHumanRole> getTaskInitiator() {
		return getGenericHumanRole().list(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__TASK_INITIATOR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TGenericHumanRole> getTaskStakeholders() {
		return getGenericHumanRole().list(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__TASK_STAKEHOLDERS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TGenericHumanRole> getBusinessAdministrators() {
		return getGenericHumanRole().list(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__BUSINESS_ADMINISTRATORS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TGenericHumanRole> getRecipients() {
		return getGenericHumanRole().list(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__RECIPIENTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case htdPackage.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE:
				return ((InternalEList<?>)getGenericHumanRole()).basicRemove(otherEnd, msgs);
			case htdPackage.TPEOPLE_ASSIGNMENTS__POTENTIAL_OWNERS:
				return ((InternalEList<?>)getPotentialOwners()).basicRemove(otherEnd, msgs);
			case htdPackage.TPEOPLE_ASSIGNMENTS__EXCLUDED_OWNERS:
				return ((InternalEList<?>)getExcludedOwners()).basicRemove(otherEnd, msgs);
			case htdPackage.TPEOPLE_ASSIGNMENTS__TASK_INITIATOR:
				return ((InternalEList<?>)getTaskInitiator()).basicRemove(otherEnd, msgs);
			case htdPackage.TPEOPLE_ASSIGNMENTS__TASK_STAKEHOLDERS:
				return ((InternalEList<?>)getTaskStakeholders()).basicRemove(otherEnd, msgs);
			case htdPackage.TPEOPLE_ASSIGNMENTS__BUSINESS_ADMINISTRATORS:
				return ((InternalEList<?>)getBusinessAdministrators()).basicRemove(otherEnd, msgs);
			case htdPackage.TPEOPLE_ASSIGNMENTS__RECIPIENTS:
				return ((InternalEList<?>)getRecipients()).basicRemove(otherEnd, msgs);
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
			case htdPackage.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE:
				if (coreType) return getGenericHumanRole();
				return ((FeatureMap.Internal)getGenericHumanRole()).getWrapper();
			case htdPackage.TPEOPLE_ASSIGNMENTS__POTENTIAL_OWNERS:
				return getPotentialOwners();
			case htdPackage.TPEOPLE_ASSIGNMENTS__EXCLUDED_OWNERS:
				return getExcludedOwners();
			case htdPackage.TPEOPLE_ASSIGNMENTS__TASK_INITIATOR:
				return getTaskInitiator();
			case htdPackage.TPEOPLE_ASSIGNMENTS__TASK_STAKEHOLDERS:
				return getTaskStakeholders();
			case htdPackage.TPEOPLE_ASSIGNMENTS__BUSINESS_ADMINISTRATORS:
				return getBusinessAdministrators();
			case htdPackage.TPEOPLE_ASSIGNMENTS__RECIPIENTS:
				return getRecipients();
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
			case htdPackage.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE:
				((FeatureMap.Internal)getGenericHumanRole()).set(newValue);
				return;
			case htdPackage.TPEOPLE_ASSIGNMENTS__POTENTIAL_OWNERS:
				getPotentialOwners().clear();
				getPotentialOwners().addAll((Collection<? extends TGenericHumanRole>)newValue);
				return;
			case htdPackage.TPEOPLE_ASSIGNMENTS__EXCLUDED_OWNERS:
				getExcludedOwners().clear();
				getExcludedOwners().addAll((Collection<? extends TGenericHumanRole>)newValue);
				return;
			case htdPackage.TPEOPLE_ASSIGNMENTS__TASK_INITIATOR:
				getTaskInitiator().clear();
				getTaskInitiator().addAll((Collection<? extends TGenericHumanRole>)newValue);
				return;
			case htdPackage.TPEOPLE_ASSIGNMENTS__TASK_STAKEHOLDERS:
				getTaskStakeholders().clear();
				getTaskStakeholders().addAll((Collection<? extends TGenericHumanRole>)newValue);
				return;
			case htdPackage.TPEOPLE_ASSIGNMENTS__BUSINESS_ADMINISTRATORS:
				getBusinessAdministrators().clear();
				getBusinessAdministrators().addAll((Collection<? extends TGenericHumanRole>)newValue);
				return;
			case htdPackage.TPEOPLE_ASSIGNMENTS__RECIPIENTS:
				getRecipients().clear();
				getRecipients().addAll((Collection<? extends TGenericHumanRole>)newValue);
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
			case htdPackage.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE:
				getGenericHumanRole().clear();
				return;
			case htdPackage.TPEOPLE_ASSIGNMENTS__POTENTIAL_OWNERS:
				getPotentialOwners().clear();
				return;
			case htdPackage.TPEOPLE_ASSIGNMENTS__EXCLUDED_OWNERS:
				getExcludedOwners().clear();
				return;
			case htdPackage.TPEOPLE_ASSIGNMENTS__TASK_INITIATOR:
				getTaskInitiator().clear();
				return;
			case htdPackage.TPEOPLE_ASSIGNMENTS__TASK_STAKEHOLDERS:
				getTaskStakeholders().clear();
				return;
			case htdPackage.TPEOPLE_ASSIGNMENTS__BUSINESS_ADMINISTRATORS:
				getBusinessAdministrators().clear();
				return;
			case htdPackage.TPEOPLE_ASSIGNMENTS__RECIPIENTS:
				getRecipients().clear();
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
			case htdPackage.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE:
				return genericHumanRole != null && !genericHumanRole.isEmpty();
			case htdPackage.TPEOPLE_ASSIGNMENTS__POTENTIAL_OWNERS:
				return !getPotentialOwners().isEmpty();
			case htdPackage.TPEOPLE_ASSIGNMENTS__EXCLUDED_OWNERS:
				return !getExcludedOwners().isEmpty();
			case htdPackage.TPEOPLE_ASSIGNMENTS__TASK_INITIATOR:
				return !getTaskInitiator().isEmpty();
			case htdPackage.TPEOPLE_ASSIGNMENTS__TASK_STAKEHOLDERS:
				return !getTaskStakeholders().isEmpty();
			case htdPackage.TPEOPLE_ASSIGNMENTS__BUSINESS_ADMINISTRATORS:
				return !getBusinessAdministrators().isEmpty();
			case htdPackage.TPEOPLE_ASSIGNMENTS__RECIPIENTS:
				return !getRecipients().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (genericHumanRole: ");
		result.append(genericHumanRole);
		result.append(')');
		return result.toString();
	}

} //TPeopleAssignmentsImpl
