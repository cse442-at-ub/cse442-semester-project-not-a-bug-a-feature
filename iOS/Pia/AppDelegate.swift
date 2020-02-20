//
//  AppDelegate.swift
//  Pia
//
//  Created by apple on 2/17/20.
//  Copyright © 2020 apple. All rights reserved.
//

import UIKit
import Amplify
import AmplifyPlugins
@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {




    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
         let apiPlugin = AWSAPIPlugin(modelRegistration: AmplifyModels())
           do {
               try Amplify.add(plugin: apiPlugin)
               try Amplify.configure()
               print("Amplify initialized")
           } catch {
               print("Failed to configure Amplify \(error)")
           }
        
        
           return true
    }

    // MARK: UISceneSession Lifecycle

    func application(_ application: UIApplication, configurationForConnecting connectingSceneSession: UISceneSession, options: UIScene.ConnectionOptions) -> UISceneConfiguration {
        // Called when a new scene session is being created.
        // Use this method to select a configuration to create the new scene with.
        return UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
    }

    func application(_ application: UIApplication, didDiscardSceneSessions sceneSessions: Set<UISceneSession>) {
        // Called when the user discards a scene session.
        // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
        // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
    }

    func apiMutate() {
        let note = Note(content: "tes test test")
        Amplify.API.mutate(of: note, type: .create) { (event) in
            switch event {
            case .completed(let result):
                switch result {
                case .success(let note):
                    print("API Mutate successful, created note: \(note)")
                case .failure(let error):
                    print("Completed with error: \(error.errorDescription)")
                }
            case .failed(let error):
                print("Failed with error \(error.errorDescription)")
            default:
                print("Unexpected event")
            }
        }
    }
    func apiQuery(id: String) {
        Amplify.API.query(from: Note.self, byId: id) { (event) in
            switch event {
            case .completed(let result):
                switch result {
                case .success(let note):
                    guard let note = note else {
                        print("API Query completed but missing note")
                        return
                    }
                    print("API Query successful, got note: \(note)")
                case .failure(let error):
                    print("Completed with error: \(error.errorDescription)")
                }
            case .failed(let error):
                print("Failed with error \(error.errorDescription)")
            default:
                print("Unexpected event")
            }
        }
    }
    func createSubscription() {
        let subscriptionOperation = Amplify.API.subscribe(from: Note.self, type: .onCreate) { (event) in
            switch event {
            case .inProcess(let subscriptionEvent):
                switch subscriptionEvent {
                case .connection(let subscriptionConnectionState):
                    print("Subsription connect state is \(subscriptionConnectionState)")
                case .data(let result):
                    switch result {
                    case .success(let todo):
                        print("Successfully got note from subscription: \(todo)")
                    case .failure(let error):
                        print("Got failed result with \(error.errorDescription)")
                    }
                }
            case .completed:
                print("Subscription has been closed")
            case .failed(let error):
                print("Got failed result with \(error.errorDescription)")
            default:
                print("Should never happen")
            }
        }
    }

}

