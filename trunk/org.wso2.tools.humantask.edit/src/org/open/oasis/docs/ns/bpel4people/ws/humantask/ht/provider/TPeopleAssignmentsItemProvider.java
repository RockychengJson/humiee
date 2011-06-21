/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPeopleAssignments;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

/**
 * This is the item provider adapter for a {@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TPeopleAssignments} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TPeopleAssignmentsItemProvider
	extends TExtensibleElementsItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TPeopleAssignmentsItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns TPeopleAssignments.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TPeopleAssignments"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_TPeopleAssignments_type");
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(TPeopleAssignments.class)) {
			case htdPackage.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__POTENTIAL_OWNERS,
					 htdFactory.eINSTANCE.createTGenericHumanRole())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__EXCLUDED_OWNERS,
					 htdFactory.eINSTANCE.createTGenericHumanRole())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__TASK_INITIATOR,
					 htdFactory.eINSTANCE.createTGenericHumanRole())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__TASK_STAKEHOLDERS,
					 htdFactory.eINSTANCE.createTGenericHumanRole())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__BUSINESS_ADMINISTRATORS,
					 htdFactory.eINSTANCE.createTGenericHumanRole())));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__GENERIC_HUMAN_ROLE,
				 FeatureMapUtil.createEntry
					(htdPackage.Literals.TPEOPLE_ASSIGNMENTS__RECIPIENTS,
					 htdFactory.eINSTANCE.createTGenericHumanRole())));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		if (childFeature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature)childFeature)) {
			FeatureMap.Entry entry = (FeatureMap.Entry)childObject;
			childFeature = entry.getEStructuralFeature();
			childObject = entry.getValue();
		}

		boolean qualify =
			childFeature == htdPackage.Literals.TPEOPLE_ASSIGNMENTS__POTENTIAL_OWNERS ||
			childFeature == htdPackage.Literals.TPEOPLE_ASSIGNMENTS__EXCLUDED_OWNERS ||
			childFeature == htdPackage.Literals.TPEOPLE_ASSIGNMENTS__TASK_INITIATOR ||
			childFeature == htdPackage.Literals.TPEOPLE_ASSIGNMENTS__TASK_STAKEHOLDERS ||
			childFeature == htdPackage.Literals.TPEOPLE_ASSIGNMENTS__BUSINESS_ADMINISTRATORS ||
			childFeature == htdPackage.Literals.TPEOPLE_ASSIGNMENTS__RECIPIENTS;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
