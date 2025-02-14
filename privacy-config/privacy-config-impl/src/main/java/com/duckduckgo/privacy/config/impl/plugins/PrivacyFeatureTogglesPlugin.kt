/*
 * Copyright (c) 2021 DuckDuckGo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duckduckgo.privacy.config.impl.plugins

import com.duckduckgo.app.global.DispatcherProvider
import com.duckduckgo.di.scopes.AppObjectGraph
import com.duckduckgo.feature.toggles.api.FeatureTogglesPlugin
import com.duckduckgo.feature.toggles.api.FeatureName
import com.duckduckgo.privacy.config.api.PrivacyFeatureName
import com.duckduckgo.privacy.config.store.PrivacyFeatureToggles
import com.duckduckgo.privacy.config.store.PrivacyFeatureTogglesRepository
import com.squareup.anvil.annotations.ContributesMultibinding
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@ContributesMultibinding(AppObjectGraph::class)
class PrivacyFeatureTogglesPlugin @Inject constructor(private val privacyFeatureTogglesRepository: PrivacyFeatureTogglesRepository, private val dispatcherProvider: DispatcherProvider) :
    FeatureTogglesPlugin {

    override fun isEnabled(featureName: FeatureName, defaultValue: Boolean): Boolean? {
        return runBlocking(dispatcherProvider.io()) {
            if (featureName is PrivacyFeatureName) {
                val privacyFeature: PrivacyFeatureToggles? = privacyFeatureTogglesRepository.get(featureName.value)
                privacyFeature?.let {
                    return@runBlocking privacyFeature.enabled
                }
                return@runBlocking defaultValue
            }
            return@runBlocking null
        }
    }

}
