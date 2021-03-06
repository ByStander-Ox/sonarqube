/*
 * SonarQube
 * Copyright (C) 2009-2020 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
import * as React from 'react';
import { translate } from 'sonar-ui-common/helpers/l10n';
import { createGithubConfiguration, updateGithubConfiguration } from '../../../../api/almSettings';
import { ALM_KEYS, GithubBindingDefinition } from '../../../../types/alm-settings';
import AlmTab from './AlmTab';
import GithubForm from './GithubForm';

export interface GithubTabProps {
  definitions: GithubBindingDefinition[];
  loading: boolean;
  multipleAlmEnabled: boolean;
  onDelete: (definitionKey: string) => void;
  onUpdateDefinitions: () => void;
}

export default function GithubTab(props: GithubTabProps) {
  const { multipleAlmEnabled, definitions, loading } = props;

  return (
    <AlmTab
      additionalColumnsHeaders={[
        translate('settings.pr_decoration.table.column.github.url'),
        translate('settings.pr_decoration.table.column.app_id')
      ]}
      additionalColumnsKeys={['appId', 'url']}
      alm={ALM_KEYS.GITHUB}
      createConfiguration={createGithubConfiguration}
      defaultBinding={{ key: '', appId: '', url: '', privateKey: '' }}
      definitions={definitions}
      form={childProps => <GithubForm {...childProps} />}
      loading={loading}
      multipleAlmEnabled={multipleAlmEnabled}
      onDelete={props.onDelete}
      onUpdateDefinitions={props.onUpdateDefinitions}
      updateConfiguration={updateGithubConfiguration}
    />
  );
}
