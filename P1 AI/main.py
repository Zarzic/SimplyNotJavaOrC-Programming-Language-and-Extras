from collections import deque
from queue import PriorityQueue
import copy
#####################################################################################################
#Helper Classes
#####################################################################################################
class Node:
    """A node in a search tree. Contains a pointer to the parent (the node
    that this is a successor of) and to the actual state for this node. Note
    that if a state is arrived at by two paths, then there are two nodes with
    the same state. Also includes the action that got us to this state, and
    the total path_cost (also known as g) to reach the node. Other functions
    may add an f and h value; see best_first_graph_search and astar_search for
    an explanation of how the f and h values are handled. You will not need to
    subclass this class."""

    def __init__(self, value, md=None, neighbors=None):
      self.md = md
      self.value = value
      if neighbors is None:
        self.neighbors = []
      else:
        self.neighbors = neighbors

    def has_neighbors(self):
      if len(self.neighbors) == 0:
        return False
      return True

    def number_of_neighbors(self):
      return len(self.neighbors)
      
    def add_neighbor(self, neighbor):
      self.neighbors.append(neighbor)
    
    def __lt__(self, other):
      return (self.md < other.md)

class Graph:
  def __init__(self, nodes=Node):
    if nodes is None:
      self.nodes = []
    else:
      self.nodes = nodes

  def add_node(self, value, md, neighbors=None):
    self.nodes.append(Node(value, md, neighbors))

  def find_node(self, value):
    for node in self.nodes:
      if node.value == value:
        return node
    return None

  def add_edges(self, value1, value2):
    node1 = self.find_node(value1)
    node2 = self.find_node(value2)
    if (node1 is not None) and (node2 is not None):
      node1.add_neighbor(node2)
      node2.add_neighbor(node1)
    else:
      print("error")

  def number_of_nodes(self):
    return f"the graph has {len(self.nodes)}"

  def are_connected(self, node1, node2):
    node1 = self.find_node(node1)
    node2 = self.find_node(node2)
    for neighbor in node1.neighbors:
      if neighbor.value == node2.value:
        return True
    return False
#############################################################################################
#Initial Set up for One Goal
#############################################################################################
file1 = open("mazeFiles/smallMaze.lay", "r")
lines = file1.read().splitlines()
lis = []
for x in lines:
    lis.append(list(x))

inside_width = len(lis[0]) - 2
inside_height = len(lis) - 2

point_pair = [(ix,iy) for ix, row in enumerate(lis) for iy, i in enumerate(row) if i == '.']
point_y = point_pair[0][0]
point_x = point_pair[0][1]

pac_pair = [(ix,iy) for ix, row in enumerate(lis) for iy, i in enumerate(row) if i == 'P']
pac_y = pac_pair[0][0]
pac_x = pac_pair[0][1]

#############################################################################################
#One goal specfic / printing solutions
#############################################################################################
g1 = Graph(None)
for i in range(1,inside_height + 1):
  for yy in range(1,len(lis[i])):
    if lis[i][yy] == ' ' and i == 1:
      g1.add_node((i,yy), (abs(i - point_y) + abs(yy - point_x)))
      created_node_par = (i,yy)
      if lis[i][yy-1] != '%':
        g1.add_edges(created_node_par, (i,yy-1))
    elif lis[i][yy] == ' ' and i != 1:
      g1.add_node((i,yy), (abs(i - point_y) + abs(yy - point_x)))
      created_node_par = (i,yy)
      if lis[i][yy-1] != '%':
        g1.add_edges(created_node_par, (i,yy-1))
      if lis[i - 1][yy] != '%':
        g1.add_edges(created_node_par, (i-1,yy))
    elif lis[i][yy] == 'P':
      g1.add_node((i,yy), (abs(i - point_y) + abs(yy - point_x)))
      created_node_par = (i,yy)
      if lis[i][yy-1] != '%':
        g1.add_edges(created_node_par, (i,yy-1))
      if lis[i - 1][yy] != '%':
        g1.add_edges(created_node_par, (i-1,yy))
    elif lis[i][yy] == '.':
      g1.add_node((i,yy),0)
      created_node_par = (i,yy)
      if lis[i][yy-1] != '%':
        g1.add_edges(created_node_par, (i,yy-1))
      if lis[i - 1][yy] != '%':
        g1.add_edges(created_node_par, (i-1,yy))
    else:
      continue
print("")
print("MAZE:")
print("")
for xx in lis:
  print(xx)
print("")
print("")
#####################################################################################################
#BFS and A* Search For Single ONLY
#####################################################################################################
def bfs_one_goal(graph, value):
  queue = []
  visited = []
  queue.append(graph.find_node(value))
  depth = 0
  max_fringe = 0
  max_expansion = 0
  if graph.find_node(value) is None:
    return None
  while queue:
    level_size = len(queue)
    if max_fringe < level_size:
      max_fringe = level_size
    while (level_size != 0):
      temp = queue[0]
      if (temp.value in visited):
        level_size = level_size - 1
        queue.remove(temp)
        continue
      else:
        visited.append(temp.value)
        queue.remove(temp)
        if (temp.value == (point_y, point_x)):
          return depth, max_fringe, max_expansion, visited
        max_expansion = max_expansion + 1
        for element in temp.neighbors:
          if element.value not in visited:
            queue.append(element)
        level_size = level_size - 1
      depth = depth + 1

def A_star_one_goal(graph,value):
  queue = PriorityQueue()
  visited = []
  queue.put((graph.find_node(value).md, (graph.find_node(value), 0)))
  max_fringe = 0
  max_expansion = 0
  max_depth = 0
  if graph.find_node(value) is None:
    return None
  while not queue.empty():
    level_size = queue.qsize()
    if max_fringe < level_size:
      max_fringe = level_size
    temp = queue.get()
    depth = temp[1][1]
    if (temp[1][0].value in visited):
      continue
    else:
      max_depth = max(max_depth, depth)
      visited.append(temp[1][0].value)
      if (temp[1][0].value == (point_y, point_x)):
        return max_depth, max_fringe, max_expansion, visited
      max_expansion = max_expansion + 1
      for element in temp[1][0].neighbors:
        if element.value not in visited:
          queue.put((element.md, (element, depth + 1)))

#############################################################################################
#One goal specfic solutions with BFS and A* below
#############################################################################################
print("BFS:")
print("")
ans1 = bfs_one_goal(g1, (pac_y, pac_x))
print("Max Depth = " + str(ans1[0]))
print("Max Fringe = " + str(ans1[1]))
print("Max Expanded Nodes = " + str(ans1[2]))
visited_1 = list(ans1[3])
reversed_visited = [item[::-1] for item in visited_1]
list_list = []
for bb in reversed_visited:
  y = list(bb)
  y[1] = abs(y[1] - (inside_height + 1))
  cc = tuple(y)
  list_list.append(cc)
print("Solution Moves (X,Y) (Bottom left is (0,0)) = " + str(list_list))
print("Path Cost = " + str(len(ans1[3])))
print("")
changed1 = copy.deepcopy(lis)
changed1.reverse()
#ir = y
#ic = x
for ir, row in enumerate(changed1):
  for ic, col in enumerate(row):
    temp_tuple = (ic,ir)
    if changed1[ir][ic] == 'P':
      continue
    elif changed1[ir][ic] == '.':
      changed1[ir][ic] = "G"
    elif temp_tuple in list_list:
      changed1[ir][ic] = '~'
changed1.reverse()
print("Graphical Representation of BFS for One Goal:")
print("")
for xx in changed1:
  print(xx)
print("")
print("A* Search:")
print("")

ans2 = A_star_one_goal(g1, (pac_y, pac_x))
print("Max Depth = " + str(ans2[0]))
print("Max Fringe = " + str(ans2[1]))
print("Max Expanded Nodes = " + str(ans2[2]))

visited_2 = list(ans2[3])
reversed_visited2 = [item[::-1] for item in visited_2]
list_list2 = []
for bb2 in reversed_visited2:
  y2 = list(bb2)
  y2[1] = abs(y2[1] - (inside_height + 1))
  cc2 = tuple(y2)
  list_list2.append(cc2)

print("Solution Moves (X,Y) (Bottom left is (0,0)) = " + str(list_list2))
print("Path Cost = " + str(len(ans2[3])))
print("")

changed2 = copy.deepcopy(lis)
changed2.reverse()
#ir = y
#ic = x
for ir, row in enumerate(changed2):
  for ic, col in enumerate(row):
    temp_tuple = (ic,ir)
    if changed2[ir][ic] == 'P':
      continue
    elif changed2[ir][ic] == '.':
      changed2[ir][ic] = "G"
    elif temp_tuple in list_list2:
      changed2[ir][ic] = '~'
changed2.reverse()
print("Graphical Representation of A* Search for One Goal:")
print("")
for xx in changed2:
  print(xx)










