package tamseo.resto.services;

import tamseo.resto.services.impl.TablesServiceImpl;

public class RestoServiceFactory {

  public static final String API_URL = "/api";
  
  private static RestoServiceFactory restoServiceFactory = new RestoServiceFactory();
  private RestoAuthService restoAuthService = new RestoAuthService();
  private TablesService tablesService = new TablesServiceImpl();
  
  public static RestoServiceFactory getInstance() {
    return restoServiceFactory;
  }
  
  public RestoAuthService getRestoAuthService() {
    return restoAuthService;
  }
  
  public TablesService getTablesService() {
    return tablesService;
  }
  
  
}
