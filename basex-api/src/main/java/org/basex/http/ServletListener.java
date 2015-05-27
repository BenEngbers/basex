package org.basex.http;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This class creates and destroys HTTP sessions and servlet contexts.
 *
 * @author BaseX Team 2005-15, BSD License
 * @author Christian Gruen
 */
public final class ServletListener implements HttpSessionListener, ServletContextListener {
  /** Sessions. */
  private static HashMap<String, HttpSession> sessions;

  @Override
  public void sessionCreated(final HttpSessionEvent event) {
    final HttpSession sess =  event.getSession();
    sessions().put(sess.getId(), sess);
  }

  @Override
  public void sessionDestroyed(final HttpSessionEvent event) {
    sessions().remove(event.getSession().getId());
  }

  /**
   * Initializes the HTTP context.
   * @return context;
   */
  public static synchronized HashMap<String, HttpSession> sessions() {
    if(sessions == null) sessions = new HashMap<>();
    return sessions;
  }

  @Override
  public void contextInitialized(final ServletContextEvent event) {
  }

  @Override
  public void contextDestroyed(final ServletContextEvent event) {
    HTTPContext.close();
  }
}
