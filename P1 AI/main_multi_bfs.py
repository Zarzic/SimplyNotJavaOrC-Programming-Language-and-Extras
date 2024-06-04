from collections import deque
import copy
from ordered_set import OrderedSet
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
      if neighbor[0].value == node2.value:
        return True
    return False
#############################################################################################
#Multi-Goal Scanning Below (BFS Multi Only)
#############################################################################################
file1 = open("mazeFiles/trickySearch.lay", "r")
lines = file1.read().splitlines()
lis = []
for x in lines:
    lis.append(list(x))

inside_width = len(lis[0]) - 2
inside_height = len(lis) - 2

pac_pair = [(ix,iy) for ix, row in enumerate(lis) for iy, i in enumerate(row) if i == 'P']
pac_y = pac_pair[0][0]
pac_x = pac_pair[0][1]

list_of_points = []

g1 = Graph(None)
for i in range(1,inside_height + 1):
  for yy in range(1,len(lis[i])):
    if lis[i][yy] == ' ' and i == 1:
      g1.add_node((i,yy), 0)
      created_node_par = (i,yy)
      if lis[i][yy-1] != '%':
        g1.add_edges(created_node_par, (i,yy-1))
    elif lis[i][yy] == ' ' and i != 1:
      g1.add_node((i,yy), 0)
      created_node_par = (i,yy)
      if lis[i][yy-1] != '%':
        g1.add_edges(created_node_par, (i,yy-1))
      if lis[i - 1][yy] != '%':
        g1.add_edges(created_node_par, (i-1,yy))
    elif lis[i][yy] == 'P':
      g1.add_node((i,yy), 0)
      created_node_par = (i,yy)
      if lis[i][yy-1] != '%':
        g1.add_edges(created_node_par, (i,yy-1))
      if lis[i - 1][yy] != '%':
        g1.add_edges(created_node_par, (i-1,yy))
    elif lis[i][yy] == '.':
      g1.add_node((i,yy), 0)
      list_of_points.append((i,yy))
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

list_of_points2 = copy.deepcopy(list_of_points)
#############################################################################################
#(BFS Multi Only) Solution Function
#############################################################################################
def bfs_mul_goal(graph, value):
  queue = deque([(graph.find_node(value), [graph.find_node(value).value])])
  visited = set()
  depth = 0
  max_fringe = 0
  max_expansion = 0
  global list_list_yeah
  if graph.find_node(value) is None:
    return None
  while queue:
    level_size = len(queue)
    if max_fringe < level_size:
      max_fringe = level_size
    while (level_size != 0):
      temp, path = queue.popleft()
      visited.add(temp)
      if (temp.value in list_of_points):
        visited_1 = path
        reversed_visited = [item[::-1] for item in visited_1]
        list_list = []
        for bb in reversed_visited:
          y = list(bb)
          y[1] = abs(y[1] - (inside_height + 1))
          cc = tuple(y)
          list_list.append(cc)
        list_of_points.remove(temp.value)
        for yyy in list_list:
          list_list_yeah.add(yyy)
        if (len(list_of_points) == 0):
            return depth, max_fringe, max_expansion
      max_expansion = max_expansion + 1
      for element in temp.neighbors:
        if element not in visited:
          new_path = path + [element.value]
          queue.append((element, new_path))
      level_size = level_size - 1
    depth = depth + 1
  return None
#############################################################################################
#Multi goal specfic solutions with BFS and A* below
#############################################################################################
list_list_yeah = OrderedSet()
ans1 = bfs_mul_goal(g1, (pac_y, pac_x))

yah = list_of_points2
reversed_points = [item[::-1] for item in yah]
corrected_points = []
for bb in reversed_points:
  y = list(bb)
  y[1] = abs(y[1] - (inside_height + 1))
  cc = tuple(y)
  corrected_points.append(cc)
print("Goals at Marked (G): " + str(corrected_points))
print("Solution Moves (X,Y) (Bottom left is (0,0)): " + str(list_list_yeah))
print("Max Depth: " + str(ans1[0]))
print("Max Fringe: " + str(ans1[1]))
print("Max Expanded Nodes: " + str(ans1[2]))
print("Path Cost: " + str(len(list_list_yeah) - 1))
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
    elif temp_tuple in list_list_yeah:
      changed1[ir][ic] = '~'
changed1.reverse()
print("Graphical Representation of BFS for Multi-Goal:")
print("")
for xx in changed1:
  print(xx)