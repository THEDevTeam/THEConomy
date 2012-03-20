package net.thedevteam.theconomy;

import java.util.logging.Level;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.plugin.ServiceManager.ServicePriority;
import org.spout.api.plugin.services.EconomyService;

/**
 * THEConomy main class.
 */
public class THEConomy extends CommonPlugin {
    private THEConomyService service;

    @Override
    public void onEnable() {
        getGame().getServiceManager().register(EconomyService.class, service, this, ServicePriority.High);
        getLogger().log(Level.INFO, "THEConomy version {0} enabled.", getDescription().getVersion());
    }

    @Override
    public void onDisable() {
        getGame().getServiceManager().unregisterAll(this);
        getLogger().log(Level.INFO, "THEConomy version {0} disabled.", getDescription().getVersion());
    }

}
