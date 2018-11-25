# MongoDB Archetype Reactive
Following are the steps to make archetype for Maven : 
1. <code>git clone</code>
2. <code>cd archetype-mongodb</code>
3. <code>mvn clean install</code>
4. <code>mvn archetype:create-from-project</code>
5. <code>cd target/generated-sources/archetype</code>
6. <code>mvn install</code>
7. Done!!
Now you can create a new Maven Project by just running <code>mvn archetype:generate -DarchetypeCatalog=local</code>


# Final Project BackEnd Details

Layout Structure: Controller -> Service -> Data Access Object(DAO)<br/>
  Controller is where the endpoints are.
  Service contains the logical functionalities.
  DAO consist of the repository interfaces that will be created in the database; Each repository interface represents a collection.
  Class Models are used to be the models for requests, responses, and database collections' fields.
  Requests and Responses are in rest-web-models, collections model are in Entity

UserController: Manages the CRUD and applications related to user's credentials, including admin/owner
  addUser - adds a user to the database
  signIn - user login to the app
  getUser - get a user's record from the database
  logout - user logout from the app
  findAll - get all users in the database
  
SecuritySystemController: Managing and checking whether the Security System is on or off, but right now it is not being used
  get - checking the current status of the system
  update - switch the system status on or off
  
ImageController: Manages all image-related things such as captured images by camera and selfies for members of the house
  getAnImage - get an image from cloudinary
  getImages - get all images from cloudinary
  getWarningImages - get images from cloudinary that consist of warning messages only
  uploadCapturedImageToCloudinary - upload captured images by cctv to cloudinary
  uploadSelfieImageToCloudinary - upload selfie image of the users to cloudinary
  updateCapturedImageMessage - insert message for the captured image
  updateEmergencyNotification - set isRead as true if user has already read the push notification
  
ErrorHandlerController: Replaces normal errors generated by java with our own error messages
  
DeveloperController: Initial setup necessary things required for our BeeJay app to work
  addUser - Adds an admin/owner to the database (since the app does not allow first-time registration as an admin)
  initializeSystem - Set security system status as on (not being used as of now)
