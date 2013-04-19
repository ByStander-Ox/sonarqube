/*
 * Sonar, open source software quality management tool.
 * Copyright (C) 2008-2012 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * Sonar is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Sonar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sonar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.wsclient.issue;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;

public class IssueQueryTest {
  @Test
  public void get_all_issues() {
    IssueQuery query = IssueQuery.create();
    assertThat(query.urlParams()).isEmpty();
  }

  @Test
  public void get_all_issues_by_parameter() {
    IssueQuery query = IssueQuery.create()
      .keys("ABCDE", "FGHIJ")
      .assignees("arthur", "perceval")
      .components("Action.java", "Filter.java")
      .componentRoots("struts")
      .resolutions("FIXED", "FALSE-POSITIVE")
      .rules("squid:AvoidCycle")
      .statuses("OPEN", "CLOSED")
      .severities("BLOCKER", "INFO")
      .userLogins("login1", "login2")
      .limit(5)
      .offset(4);

    assertThat(query.urlParams()).hasSize(11);
    assertThat(query.urlParams()).includes(entry("keys", "ABCDE,FGHIJ"));
    assertThat(query.urlParams()).includes(entry("assignees", "arthur,perceval"));
    assertThat(query.urlParams()).includes(entry("components", "Action.java,Filter.java"));
    assertThat(query.urlParams()).includes(entry("componentRoots", "struts"));
    assertThat(query.urlParams()).includes(entry("resolutions", "FIXED,FALSE-POSITIVE"));
    assertThat(query.urlParams()).includes(entry("statuses", "OPEN,CLOSED"));
    assertThat(query.urlParams()).includes(entry("severities", "BLOCKER,INFO"));
    assertThat(query.urlParams()).includes(entry("userLogins", "login1,login2"));
    assertThat(query.urlParams()).includes(entry("limit", 5));
    assertThat(query.urlParams()).includes(entry("offset", 4));
  }

  @Test
  public void should_ignore_null_values() {
    IssueQuery query = IssueQuery.create().severities(null);
    assertThat(query.urlParams()).isEmpty();
  }
}
