//
//  ContentView.swift
//  Pia
//
//  Created by apple on 2/17/20.
//  Copyright © 2020 apple. All rights reserved.
//

import SwiftUI
import Amplify
import AmplifyPlugins
struct ContentView: View {
    var body: some View {
        Button(action: {
            createSubscription()
        }, label: {
            Text("connect")
        })
      
    }
    var sendMessage:some View{
        Button(action: {apiMutate(message: "test test test")}, label: {Text("send")})
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
func apiMutate(message:String) {
    let note = Note(content: message)
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
