/*
 * Copyright © 2023 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.cdap.cdap.sourcecontrol;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import io.cdap.cdap.sourcecontrol.operationrunner.InMemorySourceControlOperationRunner;
import io.cdap.cdap.sourcecontrol.operationrunner.SourceControlOperationRunner;

/**
 *  Guice module for source control management classes.
 */
public class SourceControlModule extends AbstractModule {

  @Override
  protected void configure() {
    // TODO: CDAP-20322 add the remoteSourceControlOperationRunner
    bind(SourceControlOperationRunner.class).to(InMemorySourceControlOperationRunner.class).in(Scopes.SINGLETON);
  }
}
