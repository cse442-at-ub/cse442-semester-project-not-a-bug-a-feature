//
// Copyright 2018-2019 Amazon.com,
// Inc. or its affiliates. All Rights Reserved.
//
// SPDX-License-Identifier: Apache-2.0
//

import Foundation

public protocol PredictionsTextToSpeechOperation: AmplifyOperation<PredictionsTextToSpeechRequest,
Void, TextToSpeechResult, PredictionsError> { }

public extension HubPayload.EventName.Predictions {
    static let textToSpeech = "Predictions.textToSpeech"
}
