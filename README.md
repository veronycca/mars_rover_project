Synopsis
--------
The aim of this API is to manage squad of robotics rovers.


How to run
----------
Use added docker image to run this API on your computer (mars-rover.tgz).   
To ungzip docker image run 'docker load -i mars-rover-docker.tgz'   
To start docker run 'docker run --net=host mars-rovers:master'   
API will starts on your default host and port.


API endpoints
-------------
To post and get data from API use implemented Swagger framework (http://host:port/swagger-ui.html#/)

+ /v1/rover/grid   
    Asks to set upper-right coordinates of the plateau. Bottom left coordinates is always (0, 0).   
    Provide x and y coordinates separated by space. Values should be higher than 0, e. g. '5 5'.   
    Endpoint returns InvalidGridArgumentsException in case of invalid grid coordinates.   

+ /v1/rover/mars_rover   
    Asks to set rover's start position.   
    Rover's position and location is represented by a combination of x and y coordinates and a letter representing one 
    of the four cardinal compass points, e. g. '1 2 N'.      
    
    Endpoint returns exceptions:    
    - GridNotSetException if plateau grid was not set.   
    - InvalidRobotPositionArgumentException in case of invalid combination of position variables.
    - OutOfPlateauGridException if robot's start position is out of the plateau grid
    
    You can set as many robots as you want.
       
+ /v1/rover/result   
    Return all robots final positions represented by a combination of x and y coordinates and a letter representing one 
    of the four cardinal compass points, e. g. '1 2 N'.   
    In case of invalid instruction or if robot came out of the plateau grid returns relevant exception message.
    
