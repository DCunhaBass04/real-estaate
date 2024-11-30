package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Users.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Organization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrganizationRepository implements Serializable {

    private List<Organization> organizations = new ArrayList<>();

    /**
     * This method returns all the organizations in the list.
     *
     * @return List of organizations.
     */
    public List<Organization> getOrganizations() {
        return organizations;
    }

    /**
     * This method returns the names of all registered organizations.
     *
     * @return List of the names of all the organizations.
     */
    public List<String> getOrganizationNames(){
        List<String> names = new ArrayList<>();
        for (Organization organization : organizations) {
            names.add(organization.getVatNumber());
        }
        return names;
    }

    /**
     * This method gets the organization through the employee.
     *
     * @return The employee's organization.
     */
    public Optional<Organization> getOrganizationByEmployee(Employee employee) {

        Optional<Organization> returnOrganization = Optional.empty();

        for (Organization organization : organizations) {
            if (organization.employs(employee)) {
                returnOrganization = Optional.of(organization);
            }
        }

        return returnOrganization;
    }

    /**
     * This method gets the organization through its ID/Name/Vat Number.
     *
     * @param agency The name/ID/Number of the organization.
     * @return The organization.
     */
    public Optional<Organization> getOrganizationByVatNumber(String agency){

        Optional<Organization> returnOrganization = Optional.empty();

        for (int i = 0; i < organizations.size(); i++) {
            Organization organization = organizations.get(i);
            String otherVatNumber = organization.getVatNumber();
            if (agency.equals(otherVatNumber)) {
                returnOrganization = Optional.of(organization);
            }
        }
        return returnOrganization;
    }

    /**
     * This method gets the organization by an employee's email.
     *
     * @param email The email of an employee.
     * @return The organization of that employee.
     */
    public Optional<Organization> getOrganizationByEmployeeEmail(String email) {

        Optional<Organization> returnOrganization = Optional.empty();

        for (Organization organization : organizations) {
            if (organization.anyEmployeeHasEmail(email)) {
                returnOrganization = Optional.of(organization);
            }
        }

        return returnOrganization;
    }

    /**
     * This method adds an organization to the list of organizations.
     *
     * @param organization The organization.
     * @return adds the organization.
     */
    public Optional<Organization> add(Organization organization) {

        Optional<Organization> newOrganization = Optional.empty();
        boolean operationSuccess = false;

        if (validateOrganization(organization)) {
            newOrganization = Optional.of(organization.clone());
            operationSuccess = organizations.add(newOrganization.get());
        }

        if (!operationSuccess) {
            newOrganization = Optional.empty();
        }

        return newOrganization;

    }

    /**
     * This method checks if the organization exists.
     *
     * @param organization The organization.
     * @return If it exists or not.
     */
    private boolean validateOrganization(Organization organization) {
        boolean isValid = !organizations.contains(organization);

        return isValid;
    }
}
