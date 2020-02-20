//
//  ProfilePage.swift
//  Pia
//
//  Created by apple on 2/20/20.
//  Copyright © 2020 apple. All rights reserved.
//
import SwiftUI

struct CircleImage: View {
    var body: some View {
        
        Image("login_bg").overlay(
            Image("user")
            .clipShape(Circle())
            .overlay(
                Circle().stroke(Color.white, lineWidth: 4))
            .shadow(radius: 10)
        )
        
    }
}

struct CircleImage_Preview: PreviewProvider {
    static var previews: some View {
        CircleImage()
    }
}
