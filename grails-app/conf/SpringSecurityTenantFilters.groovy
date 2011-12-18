
import grails.plugin.multitenant.core.CurrentTenant
import grails.plugin.multitenant.core.TenantResolver

import org.codehaus.groovy.grails.commons.ConfigurationHolder


/**
 * Spring Security related Tenant filters.
 */
public class SpringSecurityTenantFilters {

	TenantResolver tenantResolver
	CurrentTenant currentTenant

	def filters = {
		if (ConfigurationHolder.config.tenant.resolver.type == "springSecurity") {
			springSecurityTenantIdentifier(controller: "*", action: "*") {
				before = {
					Integer tenantId = tenantResolver?.getTenantFromRequest(request) ?: 0
					
					currentTenant?.set tenantId
				}
			}
		}
	}
}
