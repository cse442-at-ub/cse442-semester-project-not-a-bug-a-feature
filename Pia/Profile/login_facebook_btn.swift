//
//  login_facebook_btn.swift
//  Pia
//
//  Created by apple on 2/20/20.
//  Copyright © 2020 apple. All rights reserved.
//

import SwiftUI

struct login_ui: View {
    var body: some View {
        
        Image("login_bg").overlay(
            HStack{
                Image("Google_ui").offset(y: -50)
                .padding(.bottom, -130)
                Image("Facebook_ui").offset(y:50)
                .padding(.bottom, -130)
            }
        )
    }
        
        
}

struct login_ui_Preview: PreviewProvider {
    static var previews: some View {
        login_ui()
    }
}
