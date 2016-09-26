var Nodes, Relationships;

var nodeInfo = new Array();


var edgeNum = 0;
var nodeCount = 0;

var timer;
var  timerIsActive = false;

var informationArray = new Object();

function startTimer() 
{
	//timer = setInterval(doGetStuff, 5000);
	
	timerIsActive = true;
}
	 
function doGetStuff() 
{
	var xhttp = new XMLHttpRequest();
	
 		
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) 
    {
      if(this.responseText==null)
      {
	stopTimer();
	setTimeout(function(){
   	   startTimer();
	}, 5000);
      }
      
    else
    {
	
	var jsonIn = this.responseText;
	readInJSON(jsonIn);

    }
   }
	}
	 xhttp.open("GET", "http://localhost:8080/NotLikeThisRESTServer_war_exploded/services/getLatestTree", true);
  	 xhttp.send();

}
	 
function stopTimer()
{
	clearInterval(timer);
	timerIsActive = false;
}
			
function resumeTimer()
{
	if(!timerIsActive)
		timer = setInterval(flashText, 1000);
}

function addNode(idIn, labelIn, levelIn)
{
	try
	{
		switch (levelIn) 
		{
			case "1":
			Nodes.add(
			{
				id: idIn,
				label: labelIn,
				level: levelIn,
				image: 'Images/root.png'
			});
			break;
			case "2":
			Nodes.add(
			{
				id: idIn,
				label: labelIn,
				level: levelIn,
				image: 'Images/region.png'					
			});
			break;
			case "3":
			Nodes.add(
			{
				id: idIn,
				label: labelIn,
				level: levelIn,
				image: 'Images/VPC.png'						
			});
			break;
			case "4":
			Nodes.add(
			{
				id: idIn,
				label: labelIn,
				level: levelIn,
				image: 'Images/subnetwork.png'						
			});
			break;
			case "5":
			Nodes.add(
			{
				id: idIn,
				label: labelIn,
				level: levelIn,
				image: 'Images/Instance.png'					
			});
			break;
			default:
			Nodes.add(
			{
				id: idIn,
				label: labelIn,
				level: levelIn,
				shape: 'dot',  
				color: {background:'white', border:'black',highlight:{background:'white',border:'black'}},
				font: {background: 'white'}						
			});
		}
	}
	catch (err) 
	{
		alert(err);
	}
}

function updateNode(idIn, labelIn)
{
	try 
	{
		Nodes.update(
		{
			id: idIn,
			label: labelIn
		});
	}
	catch (err)
	{
		alert(err);
	}
}

function removeNode(idIn)
{
	try
	{
		Nodes.remove({id: idIn});
	}
	catch (err)
	{
		alert(err);
	}
}

function addEdge(idIn, fromIn, toIn)
{
	try
	{
		Relationships.add(
		{
			id: idIn,
			from: fromIn,
			to: toIn,
		});
	}
	catch (err)
	{
		alert(err);
	}
}

function updateEdge(idIn, fromIn, inTo)
{
	try
	{
		Relationships.update(
		{
			id: idIn,
			from: fromIn,
			to: inTo
		});
	}
	catch (err)
	{
		alert(err);
	}
}

function removeEdge(idIn)
{
	try
	{
		Relationships.remove({id: idIn});
	}
	catch (err)
	{
		alert(err);
	}
}


var JSONThings;

var openFile = function (event)
{
	var input = event.target;
	var reader = new FileReader();
	
	reader.onload = function ()
	{
		var text = reader.result;
		console.log(reader.result.substring(0, 200));
		readInJSON(text);
	};
	
	reader.readAsText(input.files[0]);
};

//Function that starts traverse
function readInJSON(jsonIn)
{
	
	JSONThings = jsonIn;
	
	var obj = JSON.parse(jsonIn);
	
	for(i = 0; i < obj.NodesArray.length; i++)
	{
		addNode(obj.NodesArray[i].UUID, obj.NodesArray[i].Name, obj.NodesArray[i].Level);
		
		if(obj.NodesArray[i].Relationships.length != 0)
		{
			for(j = 0; j < obj.NodesArray[i].Relationships.length; j++)
			{
				addEdge(edgeNum, obj.NodesArray[i].UUID, obj.NodesArray[i].Relationships[j].UUID);
				edgeNum = edgeNum + 1;
			}
		}
	}
	
	for(i = 0; i < obj.Information.length; i++)
	{
		informationArray[obj.NodesArray[i].UUID] = obj.Information[i];
		//alert(informationArray[obj.NodesArray[i].UUID].InfoStuff);
	}
		
	
}




function traverse(node, level)
{
	nodeCount++;

	addNode(nodeCount, node.Name, level);

	level = level + 1;
	
	for (var i = 0, len = node.Children.length; i < len; i++)
	{
		//addEdge(edgeNum, nodeCount, i);
		addEdge(edgeNum, node.UUID,	node.Children[i].UUID);

		edgeNum = edgeNum + 1;
		traverse(node.Children[i], level);
	}
}


function draw()
{
	Nodes = new vis.DataSet();

	//Nodes.on('*', function ()
	//{	});

	Relationships = new vis.DataSet();

	//Relationships.on('*', function ()
	//{     });

	var options =
        {
		interaction: {navigationButtons: true, keyboard: true, hover: true, hideEdgesOnDrag: true},
                layout: {hierarchical: {direction: 'UD'}},
                nodes: {shape: 'circularImage', borderWidth:3, size:40,shapeProperties: { useBorderWithImage:true}, color: {background:'white', border:'black', highlight:{background:' #3498db ',border:' #black '}},font: {background: 'white', size: 14}	},
                edges: {width: 2},
                physics: {enabled: false},
	};

	var data =
	{
                nodes: Nodes,
                edges: Relationships
	};


	var container = document.getElementById("hierarchyVisualizerDiv");
	networkHierarchy = new vis.Network(container, data, options);

	networkHierarchy.on("selectNode", function (params)
	{	});
	
	//start Timer
	//get JSON based on timer

}