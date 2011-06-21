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

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdFactory;
import org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.htdPackage;

/**
 * This is the item provider adapter for a {@link org.open.oasis.docs.ns.bpel4people.ws.humantask.ht.TTask} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TTaskItemProvider
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
	public TTaskItemProvider(AdapterFactory adapterFactory) {
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

			addNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TTask_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TTask_name_feature", "_UI_TTask_type"),
				 htdPackage.Literals.TTASK__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
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
			childrenFeatures.add(htdPackage.Literals.TTASK__INTERFACE);
			childrenFeatures.add(htdPackage.Literals.TTASK__PRIORITY);
			childrenFeatures.add(htdPackage.Literals.TTASK__PEOPLE_ASSIGNMENTS);
			childrenFeatures.add(htdPackage.Literals.TTASK__DELEGATION);
			childrenFeatures.add(htdPackage.Literals.TTASK__PRESENTATION_ELEMENTS);
			childrenFeatures.add(htdPackage.Literals.TTASK__OUTCOME);
			childrenFeatures.add(htdPackage.Literals.TTASK__SEARCH_BY);
			childrenFeatures.add(htdPackage.Literals.TTASK__RENDERINGS);
			childrenFeatures.add(htdPackage.Literals.TTASK__DEADLINES);
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
	 * This returns TTask.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TTask"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((TTask)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_TTask_type") :
			getString("_UI_TTask_type") + " " + label;
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

		switch (notification.getFeatureID(TTask.class)) {
			case htdPackage.TTASK__NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case htdPackage.TTASK__INTERFACE:
			case htdPackage.TTASK__PRIORITY:
			case htdPackage.TTASK__PEOPLE_ASSIGNMENTS:
			case htdPackage.TTASK__DELEGATION:
			case htdPackage.TTASK__PRESENTATION_ELEMENTS:
			case htdPackage.TTASK__OUTCOME:
			case htdPackage.TTASK__SEARCH_BY:
			case htdPackage.TTASK__RENDERINGS:
			case htdPackage.TTASK__DEADLINES:
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
				(htdPackage.Literals.TTASK__INTERFACE,
				 htdFactory.eINSTANCE.createTTaskInterface()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TTASK__PRIORITY,
				 htdFactory.eINSTANCE.createTPriority()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TTASK__PEOPLE_ASSIGNMENTS,
				 htdFactory.eINSTANCE.createTPeopleAssignments()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TTASK__DELEGATION,
				 htdFactory.eINSTANCE.createTDelegation()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TTASK__PRESENTATION_ELEMENTS,
				 htdFactory.eINSTANCE.createTPresentationElements()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TTASK__OUTCOME,
				 htdFactory.eINSTANCE.createTQuery()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TTASK__SEARCH_BY,
				 htdFactory.eINSTANCE.createTExpression()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TTASK__SEARCH_BY,
				 htdFactory.eINSTANCE.createTBooleanExpr()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TTASK__SEARCH_BY,
				 htdFactory.eINSTANCE.createTDeadlineExpr()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TTASK__SEARCH_BY,
				 htdFactory.eINSTANCE.createTDurationExpr()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TTASK__SEARCH_BY,
				 htdFactory.eINSTANCE.createTPriority()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TTASK__RENDERINGS,
				 htdFactory.eINSTANCE.createTRenderings()));

		newChildDescriptors.add
			(createChildParameter
				(htdPackage.Literals.TTASK__DEADLINES,
				 htdFactory.eINSTANCE.createTDeadlines()));
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
			childFeature == htdPackage.Literals.TTASK__PRIORITY ||
			childFeature == htdPackage.Literals.TTASK__SEARCH_BY;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
