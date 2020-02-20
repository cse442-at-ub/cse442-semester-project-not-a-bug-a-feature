//
//  ProfileHost.swift
//  Pia
//
//  Created by apple on 2/20/20.
//  Copyright © 2020 apple. All rights reserved.
//
import Foundation
import SwiftUI


struct ProfileHost: View {
    

    var body: some View {
        Text("Profile for: \(draftProfile.username)")
    }
}

struct ProfileHost_Previews: PreviewProvider {
    static var previews: some View {
        ProfileHost()
    }
}
