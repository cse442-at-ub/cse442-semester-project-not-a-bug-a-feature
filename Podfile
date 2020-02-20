# Uncomment the next line to define a global platform for your project
platform :ios, '13.2'

target 'Pia' do
  # Comment the next line if you don't want to use dynamic frameworks
  use_frameworks!
  pod 'amplify-tools'

  pod 'Amplify'
  pod 'AWSPluginsCore'
  pod 'AmplifyPlugins/AWSAPIPlugin'
  
  target 'DataStoreApp' do
    pod 'amplify-tools'
    pod 'AmplifyPlugins/AWSDataStorePlugin'
  end
  # Pods for Pia

  target 'PiaTests' do
    inherit! :search_paths
    # Pods for testing
  end

  target 'PiaUITests' do
    # Pods for testing
  end
  
  

end
