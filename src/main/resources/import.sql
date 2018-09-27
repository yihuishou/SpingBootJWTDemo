CREATE TABLE `account` (
  `account` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `Uuid` varchar(128) DEFAULT NULL,
  `createDateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updateDateTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `account_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Uuid` varchar(128) DEFAULT NULL,
  `roleID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `power` (
  `powerID` int(11) NOT NULL AUTO_INCREMENT,
  `powerName` varchar(255) DEFAULT NULL,
  `powerDetials` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`powerID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `roleID` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  `roleDetials` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `role_prower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `powerID` int(11) DEFAULT NULL,
  `roleID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

